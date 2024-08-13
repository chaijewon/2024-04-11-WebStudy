package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class GoodsDAO {
  
  private static SqlSessionFactory ssf;
  static 
  {
	  ssf=CreateSqlSessionFactory.getSsf();
  }
  /*
   *   <select id="goodsListData" resultType="GoodsVO" parameterType="hashmap">
		   SELECT no,goods_name,goods_price,goods_poster,num
		   FROM (SELECT no,goods_name,goods_price,goods_poster,rownum as num 
		   FROM (SELECT no,goods_name,goods_price,goods_poster
		   FROM ${table_name} ORDER BY no ASC))
		   WHERE num BETWEEN #{start} AND #{end}
		  </select>
		  <select id="goodsTotalPage" resultType="int" parameterType="hashmap">
		   SELECT CEIL(COUNT(*)/20.0) FROM ${table_name}
		  </select>
   */
  public static List<GoodsVO> goodsListData(Map map)
  {
	  List<GoodsVO> list=new ArrayList<GoodsVO>();
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  list=session.selectList("goodsListData",map);
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
  public static int goodsTotalPage(Map map)
  {
	  int total=0;
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  total=session.selectOne("goodsTotalPage",map);
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
   *     <update id="goodsHitIncrement" parameterType="hashmap">
		    UPDATE ${table_name} SET 
		    hit=hit+1
		    WHERE no=#{no}
		  </update>
		  <select id="goodsDetailData" parameterType="hashmap">
		    SELECT * FROM ${table_name}
		    WHERE no=#{no}
		  </select>
   */
  public static GoodsVO goodsDetailData(Map map)
  {
	  GoodsVO vo=new GoodsVO();
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();
		  session.update("goodsHitIncrement",map);
		  session.commit();
		  vo=session.selectOne("goodsDetailData",map);
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
}
