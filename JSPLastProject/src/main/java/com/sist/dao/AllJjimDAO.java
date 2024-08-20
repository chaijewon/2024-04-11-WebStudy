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
   /*
    *   <update id="JjimCountIncrement" parameterType="hashmap">
		   UPDATE project_food_house SET 
		   jjimcount=(SELECT COUNT(*) FROM all_jjim WHERE type=#{type} AND cno=#{cno})
		   WHERE fno=#{cno}
		 </update>
    */
   public static void JjimCountIncrement(Map map)
   {
	      SqlSession session=null;
		  try
		  {
			  session=ssf.openSession(true);
			  session.update("JjimCountIncrement",map);
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
    *   <select id="JjimMyPageListData" resultMap="jjimMap" parameterType="string">
		   SELECT jno,cno,regdate,name,poster,address,phone
		   FROM all_jjim aj,project_food_house pf
		   WHERE aj.cno=pf.fno
		   AND id=#{id}
		   ORDER BY jno DESC
		 </select>
    */
   public static List<AllJjimVO> JjimMyPageListData(String id)
   {
	   List<AllJjimVO> list=new ArrayList<AllJjimVO>();
	   SqlSession session=null;
		  try
		  {
			  session=ssf.openSession(true);
			  list=session.selectList("JjimMyPageListData",id);
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  if(session!=null)
				  session.close();
		  }
	   return list;
   }
   /*
    *  <delete id="JjjimCancel" parameterType="int">
		  DELETE FROM all_jjim
		  WHERE jno=#{jno}
		 </delete>
    */
   public static void JjjimCancel(int jno)
   {
	      SqlSession session=null;
		  try
		  {
			  session=ssf.openSession(true);
			  session.delete("JjjimCancel",jno);
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
}
