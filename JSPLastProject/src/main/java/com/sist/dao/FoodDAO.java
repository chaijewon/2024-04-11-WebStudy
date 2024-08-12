package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class FoodDAO {
   private static SqlSessionFactory ssf;
   static
   {
	   ssf=CreateSqlSessionFactory.getSsf();
   }
   /*
    *   <select id="foodHitTopData">
		    SELECT fno,name,poster,rownum 
		    FROM (SELECT fno,name,poster 
		    FROM project_food_house ORDER BY hit DESC)
		    WHERE rownum&lt;=12
		  </select>
		  <select id="foodLikeTopData">
		    SELECT fno,name,poster,rownum 
		    FROM (SELECT fno,name,poster 
		    FROM project_food_house ORDER BY likecount DESC)
		    WHERE rownum&lt;=12
		  </select>
		  <select id="foodJjimTopData">
		    SELECT fno,name,poster,rownum 
		    FROM (SELECT fno,name,poster 
		    FROM project_food_house ORDER BY jjimcount DESC)
		    WHERE rownum&lt;=12
		  </select>
    */
   // hit가 많은 맛집 
   public static List<FoodVO> foodHitTopData()
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   list=session.selectList("foodHitTopData");
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
   // like가 많은 맛집 
   public static List<FoodVO> foodLikeTopData()
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   list=session.selectList("foodLikeTopData");
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
   // jjim이 많은 맛집 
   public static List<FoodVO> foodJjimTopData()
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   list=session.selectList("foodJjimTopData");
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
    *   <select id="foodListCount" resultType="int">
		    SELECT COUNT(*) FROM project_food_house
		  </select>
    */
   public static int foodListCount()
   {
	   int count=0;
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   count=session.selectOne("foodListCount");
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
   /*
    *    <select id="foodListData" resultType="FoodVO" parameterType="hashmap">
          SELECT fno,name,poster,num
          FROM (SELECT fno,name,poster,rownum as num 
          FROM (SELECT + INDEX_ASC(project_food_house fh_fno_pk)fno,name,poster 
		  FROM project_food_house))
		  WHERE num BETWEEN #{start} AND #{end}
		  </select>
		  <select id="foodTotalPage" resultType="int">
		    SELECT CEIL(COUNT(*)/20.0) FROM project_food_house
		  </select>
    */
   public static List<FoodVO> foodListData(Map map)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   list=session.selectList("foodListData",map);
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
   public static int foodTotalPage()
   {
	   int total=0;
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   total=session.selectOne("foodTotalPage");
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return total;
   }
   /*
    *   <update id="foodHitIncrement" parameterType="int">
		    UPDATE project_food_house SET
		    hit=hit+1
		    WHERE fno=#{fno}
		  </update>
		<select id="foodDetailData" resultType="FoodVO" parameterType="int">
		    SELECT fno,name,type,phone,address,score,theme,poster,images,time,parking,
		    content
		    FROM project_food_house
		    WHERE fno=#{fno}
		  </select>
    */
   public static FoodVO foodDetailData(int fno)
   {
	   FoodVO vo=new FoodVO();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   // 조회수 증가 
		   session.update("foodHitIncrement",fno);
		   session.commit(); // insert,update,delete
		   
		   // 데이터 읽기 
		   vo=session.selectOne("foodDetailData",fno);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return vo;
   }
   /*
    *    <select id="foodFindListData" resultType="FoodVO" parameterType="hashmap">
		    SELECT fno,name,poster,num
		    FROM (SELECT fno,name,poster,rownum as num 
		    FROM (SELECT + INDEX_ASC(project_food_house fh_fno_pk)fno,name,poster 
		    FROM project_food_house WHERE address LIKE '%'||#{ss}||'%'))
		    WHERE num BETWEEN #{start} AND #{end}
		  </select>
		  <select id="foodFindTotalPage" resultType="int">
		    SELECT CEIL(COUNT(*)/20.0) FROM project_food_house
		    WHERE address LIKE '%'||#{ss}||'%'
		  </select>
    */
   public static List<FoodVO> foodFindListData(Map map)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   list=session.selectList("foodFindListData",map);
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
   public static int foodFindTotalPage(String ss)
   {
	   int total=0;
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   total=session.selectOne("foodFindTotalPage",ss);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return total;
   }
   
   // 인근 맛집 
   /*
    *   <select id="foodRearListData" resultType="FoodVO" parameterType="string">
		    SELECT fno,name,poster,rownum
		    FROM (SELECT fno,name,poster 
		    FROM project_food_house WHERE address LIKE '%'||#{ss}||'%' ORDER BY fno ASC)
		    WHERE rownum&lt;=6
		  </select>
    */
   public static List<FoodVO> foodRearListData(String ss)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   SqlSession session=null; //Connection
	   try
	   {
		   session=ssf.openSession();
		   list=session.selectList("foodRearListData", ss);
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
   
}
