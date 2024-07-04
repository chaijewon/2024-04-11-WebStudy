package com.sist.dao;
import java.util.*;

import com.sist.database.DataBaseConnection;

import java.sql.*;
public class GoodsDAO {
  private Connection conn;
  private PreparedStatement ps;
  private static GoodsDAO dao;
  private DataBaseConnection dbConn=new DataBaseConnection();
  private String[] tabs={"","goods_all","goods_new","goods_special","goods_best"};
  // 싱글턴 
  public static GoodsDAO newInstance()
  {
	  if(dao==null)
		  dao=new GoodsDAO();
	  return dao;
  }
  /*
   *   nO                                        NOT NULL NUMBER(38)
 GOODS_NAME                                         VARCHAR2(4000)
 GOODS_SUB                                          VARCHAR2(4000)
 GOODS_PRICE                                        VARCHAR2(26)
 GOODS_DISCOUNT                                     NUMBER(38)
 GOODS_FIRST_PRICE                                  VARCHAR2(26)
 GOODS_DELIVERY                                     VARCHAR2(26)
 GOODS_POSTER                                       VARCHAR2(4000)
   */
  // 목록 = 50개
  public List<GoodsVO> goodsListData(int type,int page)
  {
	  List<GoodsVO> list=new ArrayList<GoodsVO>();
	  try
	  {
		  //1. 연결 
		  conn=dbConn.getConnection();
		  //2. 오라클에 전송할 SQL문장 제작 
		  String sql="SELECT no,goods_poster,goods_name,num "
				    +"FROM (SELECT no,goods_poster,goods_name,rownum as num "
				    +"FROM (SELECT no,goods_poster,goods_name "
				    +"FROM "+tabs[type]+")) "
				    +" WHERE num BETWEEN ? AND ?";
		  //3. 오라클 전송 
		  ps=conn.prepareStatement(sql);
		  int rowSize=12;
		  int start=(rowSize*page)-(rowSize-1);
		  int end=rowSize*page;
		   // IN OUT 
		  ps.setInt(1, start);
		  ps.setInt(2, end);
		  //4. 결과값 받기 
		  ResultSet rs=ps.executeQuery();
		  // rs에 저장된 결과값을 list에 담는다 
		  while(rs.next())
		  {
			  GoodsVO vo=new GoodsVO();
			  vo.setNo(rs.getInt(1));
			  vo.setPoster(rs.getString(2));
			  vo.setName(rs.getString(3));
			  list.add(vo);
		  }
		  rs.close();
	  }catch(Exception ex)
	  {
		  // 오류 확인 
		  ex.printStackTrace();
	  }
	  finally
	  {
		  // 닫기
		  dbConn.disConnection(conn,ps);
	  }
	  return list;
  }
  
  public int goodsListTotalPage(int type)
  {
	  int total=0;
	  try
	  {
		  
		  // 테이블 / 컬럼 => 문자열 결합 => setString() => ''
		  conn=dbConn.getConnection();
		  String sql="SELECT CEIL(COUNT(*)/12.0) "
				    +"FROM "+tabs[type];
		  ps=conn.prepareStatement(sql);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  total=rs.getInt(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  dbConn.disConnection(conn, ps);
	  }
	  return total;
  }
  public GoodsVO goodsDetailData(int no,int type)
  {
	  GoodsVO vo=new GoodsVO();
	  try
	  {
		  conn=dbConn.getConnection();
		  String sql="SELECT no,goods_poster,goods_name,goods_price,"
				    +"goods_sub,goods_delivery,goods_discount "
				    +"FROM "+tabs[type]
				    +" WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no);
		  
		  // 결과값 받기 
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  vo.setNo(rs.getInt(1));
		  vo.setPoster(rs.getString(2));
		  vo.setName(rs.getString(3));
		  String price=rs.getString(4);
		  vo.setPrice(price);
		  vo.setSub(rs.getString(5));
		  vo.setDelivery(rs.getString(6));
		  vo.setDiscount(rs.getInt(7));
		  String rp=price.replaceAll("[^0-9]", "");
		  vo.setRprice(Integer.parseInt(rp));
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  dbConn.disConnection(conn,ps);
	  }
	  return vo;
  }
}
