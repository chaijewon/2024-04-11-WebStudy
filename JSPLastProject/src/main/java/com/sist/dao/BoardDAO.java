package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.BoardVO;
public class BoardDAO {
  private static SqlSessionFactory ssf;
  static
  {
	  ssf=CreateSqlSessionFactory.getSsf();
  }
  /*
   *    <insert id="boardInsert" parameterType="BoardVO">
		  <selectKey keyProperty="no" resultType="int" order="BEFORE">
		    SELECT NVL(MAX(no)+1,1) as no FROM project_board
		  </selectKey>
		  INSERT INTO project_board(no,name,subject,content,pwd)
		  VALUES(#{no},#{name},#{subject},#{content},#{pwd})
		 </insert>
		 
		 parameterType : 매개변수 
		 resultType : 리턴형
   */
  public static void boardInsert(BoardVO vo)
  {
	  SqlSession session=null;
	  try
	  {
		  // 연결 => Connection주소 얻기  => DBCP : 미리 Connection객체 여러개 생성 
		  // Connection=>오라클 연결 시간이 오래 걸린다 (연결에 소모되는 시간을 줄일수 있다)
		  // Connection객체을 조절할 수 있다 
		  // => openSession() => autoCommit(false) => 저장이 안됨 => SELECT
		  // => openSession(true) => autoCommit(true) => INSERT,UPDATE,DELETE
		  session=ssf.openSession(true); 
		  session.insert("boardInsert",vo);
		  
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(session!=null)
			  session.close(); // 반환 (DBCP사용)
	  }
  }
  /*
   *   <select id="boardListData" resultType="BoardVO" parameterType="hashmap">
	   SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num
	   FROM (SELECT no,subject,name,regdate,hit,rownum as num 
	   FROM (SELECT no,subject,name,regdate,hit 
	   FROM project_board))
	   WHERE num BETWEEN #{start} AND #{end}
       </select>
   */
  public static List<BoardVO> boardListData(Map map)
  {
	  List<BoardVO> list=new ArrayList<BoardVO>();
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession(); 
		  list=session.selectList("boardListData",map);
		  
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(session!=null)
			  session.close(); // 반환 (DBCP사용)
	  }
	  return list;
  }
  /*
   *   <select id="boardRowCount" resultType="int">
		   SELECT COUNT(*) FROM project_board
		 </select>
   */
  // 총페이지 
  public static int boardRowCount()
  {
	  int count=0;
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession(); 
		  count=session.selectOne("boardRowCount");
		  
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(session!=null)
			  session.close(); // 반환 (DBCP사용)
	  }
	  return count;
  }
  /*
   *   <update id="hitIncrement" parameterType="int">
		   UPDATE project_board SET
		   hit=hit+1
		   WHERE no=#{no}
		 </update>
		 <!-- 
		        parameterType="string" parameterType="java.lang.String"
		        parameterType="hashmap" parameterType="java.util.Map"
		        parameterType="int"  parameterType="java.lang.Integer"
		  -->
		 <select id="boardDetailData" resultType="BoardVO" parameterType="int">
		   SELECT no,name,subject,content,
		          TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,hit
		   FROM project_board
		   WHERE no=#{no}
		 </select>
   */
  public static BoardVO boardDetailData(int no)
  {
	  BoardVO vo=new BoardVO();
	  SqlSession session=null;
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
		  if(session!=null)
			  session.close(); // 반환 (DBCP사용)
	  }
	  return vo;
  }
  public static BoardVO boardUpdateData(int no)
  {
	  BoardVO vo=new BoardVO();
	  SqlSession session=null;
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
		  if(session!=null)
			  session.close(); // 반환 (DBCP사용)
	  }
	  return vo;
  }
  /*
   *   <select id="boardGetPassword" resultType="string" parameterType="int">
		  SELECT pwd FROM project_board
		  WHERE no=#{no}
		 </select>
		 <update id="boardUpdate" parameterType="BoardVO">
		  UPDATE project_board SET
		  name=#{name},subject=#{subject},content=#{content}
		  WHERE no=#{no}
		 </update>
   */
  public static String boardUpdate(BoardVO vo)
  {
	  String result="no";
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession(); 
		  String db_pwd=session.selectOne("boardGetPassword",vo.getNo());
		  if(db_pwd.equals(vo.getPwd()))
		  {
			  result="yes";
			  session.update("boardUpdate",vo);
			  session.commit();
		  }
		  
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(session!=null)
			  session.close(); // 반환 (DBCP사용)
	  }
	  return result;
  }
  /*
   *   <delete id="boardDelete" parameterType="int">
		  DELETE FROM project_board
		  WHERE no=#{no}
		 </delete>
   */
  public static String boardDelete(int no,String pwd)
  {
	  String result="no";
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession(); 
		  String db_pwd=session.selectOne("boardGetPassword",no);
		  if(db_pwd.equals(pwd))
		  {
			  result="yes";
			  session.delete("boardDelete",no);
			  session.commit();
		  }
		  
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(session!=null)
			  session.close(); // 반환 (DBCP사용)
	  }
	  return result;
  }
}
