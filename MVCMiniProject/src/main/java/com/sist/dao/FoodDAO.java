package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.dao.DbcpConnection;
import com.sist.vo.FoodVO;
public class FoodDAO {
   private Connection conn;
   private PreparedStatement ps;
   private static FoodDAO dao;
   private DbcpConnection dbConn=new DbcpConnection();
   private final int ROWSIZE=20;
   public static FoodDAO newInstance()
   {
	   if(dao==null)
		   dao=new FoodDAO();
	   return dao;
   }
   
   public List<FoodVO> foodTop12()
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   try
	   {
		   conn=dbConn.getConnection();
		   String sql="SELECT fno,name,poster,rownum "
				     +"FROM (SELECT fno,name,poster "
				     +"FROM food_house ORDER BY hit DESC) "
				     +"WHERE rownum<=12";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FoodVO vo=new FoodVO();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setPoster(rs.getString(3));
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbConn.disConnection(conn, ps);
	   }
	   return list;
   }
   // 목록 출력 
   /*
    *   Controller는 이미 제작 완료 (스프링)
    *   => 웹 
    *      1. 요청 => 어떤 값을 보낼지 
    *                <a> => ?key=value => request.getParameter()
    *                       ==========
    *      2. 어떤 JSP에 결과를 보내줄지.. => request.setAttribute()
    *      3. 데이터베이스 (SQL문장) 
    */
   public List<FoodVO> foodListData(int page)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   try
	   {
		   conn=dbConn.getConnection();
		   String sql="SELECT fno,name,poster,num "
				     +"FROM (SELECT fno,name,poster,rownum as num "
				     +"FROM (SELECT fno,name,poster "
				     +"FROM food_house ORDER BY fno ASC)) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   
		   int start=(ROWSIZE*page)-(ROWSIZE-1);
		   int end=ROWSIZE*page;
		   
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   // selectList()
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FoodVO vo=new FoodVO();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setPoster(rs.getString(3).replace("https", "http"));
			   list.add(vo);
		   }
		   rs.close();
		   
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbConn.disConnection(conn, ps);
	   }
	   return list;
   }
   // 총페이지 구하기 
   public int foodTotalPage()
   {
	   int total=0;
	   try
	   {
		   conn=dbConn.getConnection();
		   String sql="SELECT CEIL(COUNT(*)/"+ROWSIZE+") FROM food_house";
		   ps=conn.prepareStatement(sql);
		   // selectOne()
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
}
