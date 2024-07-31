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
}
