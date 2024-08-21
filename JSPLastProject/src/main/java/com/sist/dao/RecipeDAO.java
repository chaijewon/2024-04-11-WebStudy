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
}
