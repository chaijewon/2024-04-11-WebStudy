package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class AllJjimDAO {
  private static SqlSessionFactory ssf;
  static 
  {
	  ssf=CreateSqlSessionFactory.getSsf();
  }
  /*
   *   <insert id="allJjimInsert" parameterType="hashmap">
		   INSERT INTO all_jjim VALUES(
		    (SELECT NVL(MAX(jno)+1,1) FROM all_jjim),
		    #{cno},#{type},#{id},SYSDATE
		   )
		 </insert>
  */
  public static void allJjimInsert(Map map)
  {
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession(true);
		  session.insert("allJjimInsert",map);
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
  /*
		 <select id="allJjimCheck" resultType="int" parameterType="hashmap">
		   SELECT COUNT(*) FROM all_jjim
		   WHERE cno=#{cno} AND type=#{type} AND id=#{id}
		 </select>
   */
   public static int allJjimCheck(Map map)
   {
	   int count=0;
	   SqlSession session=null;
		  try
		  {
			  session=ssf.openSession(true);
			  count=session.selectOne("allJjimCheck",map);
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  if(session!=null)
				  session.close();
		  }
	   return  count;
   }
}
