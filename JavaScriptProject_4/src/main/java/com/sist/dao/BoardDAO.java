package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
/*
 *   디벨로퍼 : 조립자 => SI/SM  
 *   개발자 : 제작자 => 솔루션 , 스타트업
 *   코더 : => 1년 
 */
public class BoardDAO {
   // XML을 읽어서 저장 => Map ("id",SQL) => 파싱 => 스프링 
   private static SqlSessionFactory ssf;
   static
   {
	   try
	   {
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   // 파일명 => 대소문자 구분
		   ssf=new SqlSessionFactoryBuilder().build(reader);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   /*
    *   <select id="boardListData" resultType="BoardVO" parameterType="hashmap">
        SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num 
        FROM (SELECT no,subject,name,regdate,hit,rownum as num 
        FROM (SELECT /*+ INDEX_DESC(board board_no_pk)no,subject,name,regdate,hit 
        FROM board))
        WHERE num BETWEEN  #{start} AND #{end}
       </select>
       
       resultType="BoardVO" => 결과값 ResultSet 
       parameterType="hashmap" => ? ps.setInt(1,1)
    */
   public static List<BoardVO> boardListData(Map map)
   {
	   List<BoardVO> list=new ArrayList<BoardVO>();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   list=session.selectList("boardListData",map);
		   //           => SQL문장 / VO
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
       }
	   finally
	   {
		   if(session!=null)
			   session.close(); // 반환 
	   }
	   return list;
   }
   /*
    *   <select id="boardRowCount" resultType="int">
         SELECT COUNT(*) FROM board
        </select>
    */
   public static int boardRowCount()
   {
	   SqlSession session=ssf.openSession();
	   int count=session.selectOne("boardRowCount");
	   session.close();
	   return count;
   }
   /*
    *   <insert id="boardInsert" parameterType="BoardVO">
		   INSERT INTO board VALUES(
		     board_no_seq.nextval,#{name},#{subject},#{content},#{pwd},SYSDATE,0
		   )
		  </insert>
    */
   public static void boardInsert(BoardVO vo)
   {
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession(true);
		   session.insert("boardInsert",vo);
		   //session.commit();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
   }
   
   // 상세보기 
   /*
    *   <update id="hitIncrement" parameterType="int">
		   UPDATE board SET 
		   hit=hit+1
		   WHERE no=#{no}
		  </update>
		  <select id="boardDetailData" resultType="BoardVO" parameterType="int">
		   SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit
		   FROM board
		   WHERE no=#{no}
		  </select>
    */
   public static BoardVO boardDetailData(int no)
   {
	   SqlSession session=null;
	   BoardVO vo=new BoardVO();
	   try
	   {
		   session=ssf.openSession();
		   session.update("hitIncrement",no);
		   session.commit();
		   
		   vo=session.selectOne("boardDetailData",no);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // connection 반환 (DBCP) => 재사용 
		   if(session!=null)
			   session.close();
	   }
	   return vo;
   }
   /*
    *   <select id="boardGetPassword" resultType="string" parameterType="int">
		   SELECT pwd FROM board
		   WHERE no=#{no}
		  </select>
		  <delete id="boardDelete" parameterType="int">
		   DELETE FROM board
		   WHERE no=#{no}
		  </delete>
    */
   public static boolean boardDelete(int no,String pwd)
   {
	   boolean bCheck=false;
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   String db_pwd=session.selectOne("boardGetPassword",no);
		   if(db_pwd.equals(pwd))
		   {
			   bCheck=true;
			   session.delete("boardDelete",no);
			   session.commit();
		   }
		   else
		   {
			   bCheck=false;
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return bCheck;
   }
   
   public static BoardVO boardUpdateData(int no)
   {
	   SqlSession session=null;
	   BoardVO vo=new BoardVO();
	   try
	   {
		   session=ssf.openSession();
		   vo=session.selectOne("boardDetailData",no);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // connection 반환 (DBCP) => 재사용 
		   if(session!=null)
			   session.close();
	   }
	   return vo;
   }
   public static boolean boardUpdate(BoardVO vo)
   {
	   boolean bCheck=false;
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   String db_pwd=session.selectOne("boardGetPassword",vo.getNo());
		   if(db_pwd.equals(vo.getPwd()))
		   {
			   bCheck=true;
			   session.update("boardUpdate",vo);
			   session.commit();
		   }
		   else
		   {
			   bCheck=false;
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return bCheck;
   }
}
