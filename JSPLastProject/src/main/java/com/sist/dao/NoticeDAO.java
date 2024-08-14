package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class NoticeDAO {
    private static SqlSessionFactory ssf;
    static
    {
    	ssf=CreateSqlSessionFactory.getSsf();
    }
    /*
     *   <select id="noticeListData" resultType="NoticeVO" parameterType="hashmap">
		   SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num
		   FROM (SELECT no,subject,name,regdate,hit,rownum as num 
		   FROM (SELECT no,subject,name,regdate,hit 
		   FROM notice ORDER BY no DESC))
		   WHERE num BETWEEN #{start} AND #{end}
		 </select>
     */
    // 목록 
    public static List<NoticeVO> noticeListData(Map map)
    {
    	List<NoticeVO> list=new ArrayList<NoticeVO>();
    	// Connection => SqlSession
    	SqlSession session=null;
    	try
    	{
    		session=ssf.openSession();
    		list=session.selectList("noticeListData",map);
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		if(session!=null)
    			session.close(); //DBCP => 반환 (재사용)
    	}
    	return list;
    }
    /*
     *   <insert id="noticeInsert" parameterType="NoticeVO">
		   <selectKey keyProperty="no" resultType="int" order="BEFORE">
		     SELECT NVL(MAX(no)+1,1) as no FROM notice
		   </selectKey>
		   INSERT INTO notice(no,type,subject,content)
		   VALUES(#{no},#{type},#{subject},#{content})
		 </insert>
     */
    // 공지추가 
    public static void noticeInsert(NoticeVO vo)
    {
    	SqlSession session=null;
    	try
    	{
    		session=ssf.openSession(true);
    		session.insert("noticeInsert",vo);
    		
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		if(session!=null)
    			session.close(); //DBCP => 반환 (재사용)
    	}
    }
    /*
     *   <select id="noticeRowCount" resultType="int">
		   SELECT COUNT(*) FROM notice
		 </select>
     */
    // 공지사항 총갯수 읽기  => 일렬로 번호 출력 
    public static int noticeRowCount()
    {
    	int count=0;
    	SqlSession session=null;
    	try
    	{
    		session=ssf.openSession(true);
    		count=session.selectOne("noticeRowCount");
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		if(session!=null)
    			session.close(); //DBCP => 반환 (재사용)
    	}
    	return count;
    }
    /*
     *   <select id="noticeTop5Data" resultType="NoticeVO">
		   SELECT no,type,subject,rownum
		   FROM (SELECT no,type,subject 
		   FROM notice ORDER BY hit DESC)
		   WHERE rownum&lt;=5
		 </select>
     */
    public static List<NoticeVO> noticeTop5Data()
    {
    	List<NoticeVO> list=new ArrayList<NoticeVO>();
    	SqlSession session=null;
    	try
    	{
    		session=ssf.openSession(true);
    		list=session.selectList("noticeTop5Data");
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		if(session!=null)
    			session.close(); //DBCP => 반환 (재사용)
    	}
    	return list;
    }
    
}
