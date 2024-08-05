package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;
public class FoodDAO {
  private static SqlSessionFactory ssf;
  static 
  {
	  try
	  {
		  // ssf에 대한 초기화 
		  Reader reader=Resources.getResourceAsReader("Config.xml");
		  // classpath => 자동 인식 => src/main/java 
		  // XML파싱한 데이터를 첨부 
		  ssf=new SqlSessionFactoryBuilder().build(reader);
		  // sql id 
		  /*
		   *   map.put("foodListData","SELECT fno,name,poster,num 
			   FROM (SELECT fno,name,poster,rownum as num 
			   FROM (SELECT fno,name,poster 
			   FROM food_house WHERE type LIKE '%'||#{type}||'%' ORDER BY fno ASC))
			   WHERE num BETWEEN #{start} AND #{end}")
		   */
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
  }
  
  public static List<FoodVO> foodListData(Map map)
  {
	  List<FoodVO> list=new ArrayList<FoodVO>();
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  // conn=dbConn.getConnection()
		  list=session.selectList("foodListData",map);
		  
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  // 반환 
		  if(session!=null)
			  session.close();
	  }
	  return list;
  }
  /*
   *     <select id="foodTotalPage" resultType="int" parameterType="string">
		   SELECT CEIL(COUNT(*)/20.0) FROM food_house
		   WHERE type LIKE '%'||#{type}||'%'
		  </select>
   */
  public static int foodTotalPage(String type)
  {
	  int total=0;
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  total=session.selectOne("foodTotalPage", type);
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
