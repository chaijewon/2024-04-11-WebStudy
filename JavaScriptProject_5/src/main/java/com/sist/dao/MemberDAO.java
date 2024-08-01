package com.sist.dao;
// 아이디 중복 체크 
// 우편번호 검색 
// => 윈도우 => window.open() 
/*
 *    브라우저 내장 객체 / 내장객체 
 *         window => open() / reload() 
 *           |
 *     ----------------------------
 *     |        |                 |
 *   document  location       history
 *     |          | href          | back() , go , forward()
 *    write()
 *    getElementById()
 *    querySelector()
 *    querySelectorAll()
 *   
 *    Date / String / Math / Array  => Jquery / Vue / React / Next
 *                                     => 변수 / 상수 / 제어문 / 연산자 / 배열 ....
 *                                     => 객체 (JSON)
 *                                     
 *    이벤트 
 *     onclick / onchange / onmouseover / onmouseout / onkeyup / onkeydown 
 *                          ========================
 *                            hover()
 */
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
/*
 *   for(int i=1;i<=4;i++)
 *   {
 *      for(int j=1;j<5-i;j++)
 *      {
 *         System.out.print("*")
 *      }
 *       System.out.println()
 *   }
 *   
 *    System.out.println("****")
 *    System.out.println("***")
 *    System.out.println("**")
 *    System.out.println("*")
 *   
 */
public class MemberDAO {
    private static SqlSessionFactory ssf;//xml을 읽어서 저장 
    static
    {
    	try
 	   {
 		   Reader reader=Resources.getResourceAsReader("Config.xml");
 		   // 파일명 => 대소문자 구분
 		   ssf=new SqlSessionFactoryBuilder().build(reader);
 		   System.out.println(ssf);
 	   }catch(Exception ex)
 	   {
 		   ex.printStackTrace();
 	   }
    }
    // 기능 설정 
    // => 표준화 
    public static List<ZipcodeVO> postFind(String dong)
    {
    	List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
    	SqlSession session=null;
    	try
    	{
    		session=ssf.openSession();
    		list=session.selectList("postFind",dong);
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
    
    public static int postFindCount(String dong)
    {
    	int count=0;
    	SqlSession session=null;
    	try
    	{
    		session=ssf.openSession();
    		count=session.selectOne("postFindCount",dong);
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		if(session!=null)
    			session.close(); // 반환 
    	}
    	return count;
    }
    /*
     *   <select id="idcheck" resultType="int" parameterType="string">
		   SELECT COUNT(*)
		   FROM member
		   WHERE id=#{id}
		 </select>
     */
    public static int idCheck(String id)
    {
    	int count=0;
    	SqlSession session=null; //connection
    	try
    	{
    		session=ssf.openSession();
    		count=session.selectOne("idcheck",id);
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		if(session!=null)
    			session.close();
    	}
    	return count;
    }
}
