package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class RecipeDAO {
  private static SqlSessionFactory ssf;
  static
  {
	  ssf=CreateSqlSessionFactory.getSsf();
  }
  
  /*
   *   <!-- 레시피 목록 -->
		 <select id="recipeListData" resultType="RecipeVO" parameterType="hashmap">
		   SELECT no,title,poster,num
		   FROM (SELECT no,title,poster,rownum as num
		   FROM (SELECT INDEX_ASC(recipe recipe_no_pk)no,title,poster
		   FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail)))
		   WHERE num BETWEEN #{start} AND #{end}
		 </select>
		 <!-- 레시피 총페이지 -->
		 <select id="recipeTotalPage" resultType="int">
		   SELECT CEIL(COUNT(*)/20.0) FROM recipe
		   WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail)
		 </select>
   */
  public static List<RecipeVO> recipeListData(Map map)
  {
	  List<RecipeVO> list=new ArrayList<RecipeVO>();
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  list=session.selectList("recipeListData", map);
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
  public static int recipeTotalCount()
  {
	  int count=0;
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  count=session.selectOne("recipeTotalCount");
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
  // 상세보기 
  /*
   *   <update id="recipeHitIncrement" parameterType="int">
		  UPDATE recipe SET 
		  hit=hit+1
		  WHERE no=#{no}
		 </update>
		 <select id="recipeDetailData" resultType="RecipeDetailVO" parameterType="int">
		   SELECT * FROM recipeDetail
		   WHERE no=#{no}
		 </select>
   */
  public static RecipeDetailVO recipeDetailData(int no)
  {
	  RecipeDetailVO vo=new RecipeDetailVO();
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  session.update("recipeHitIncrement",no);// 조회수 증가 
		  session.commit();
		  vo=session.selectOne("recipeDetailData",no);//상세 데이터
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
  // chef 관련 
  // 1. 목록 
  /*
   *    <select id="recipeChefListData" resultType="ChefVO" parameterType="hashmap">
		   SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,num
		   FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,rownum as num
		   FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2
		   FROM chef))
		   WHERE num BETWEEN #{start} AND #{end}
		 </select>
		 <select id="recipeChefTotalPage" resultType="int">
		   SELECT CEIL(COUNT(*)/50.0) FROM chef
		 </select>
   */
  public static List<ChefVO> recipeChefListData(Map map)
  {
	  List<ChefVO> list=new ArrayList<ChefVO>();
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  list=session.selectList("recipeChefListData",map);
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
  public static int recipeChefTotalPage()
  {
	  int total=0;
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  total=session.selectOne("recipeChefTotalPage");
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
   *   <select id="recipeChefMakeData" resultType="RecipeVO" parameterType="hashmap">
		   SELECT no,title,poster,num
		   FROM (SELECT no,title,poster,rownum as num
		   FROM (SELECT + INDEX_ASC(recipe recipe_no_pk)no,title,poster
		   FROM recipe WHERE chef=#{chef}))
		   WHERE num BETWEEN #{start} AND #{end}
		 </select>
		 <select id="recipeChefMakeTotalPage" resultType="int" parameterType="string">
		   SELECT CEIL(COUNT(*)/20.0) FROM recipe
		   WHERE chef=#{chef}
		 </select>
   */
  public static List<RecipeVO> recipeChefMakeData(Map map)
  {
	  List<RecipeVO> list=new ArrayList<RecipeVO>();
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  list=session.selectList("recipeChefMakeData",map);
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
  public static int recipeChefMakeTotalPage(String chef)
  {
	  int total=0;
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  total=session.selectOne("recipeChefMakeTotalPage",chef);
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
}
