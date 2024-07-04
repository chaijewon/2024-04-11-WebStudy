package com.sist.dao;
import java.util.*;
import java.util.concurrent.Executor;
import java.sql.*;
import com.sist.database.*;
/*
 *    1. 오라클 연결 => TCP
 *       Connection : Socket
 *                    ======= IP / PORT 
 *    2. SQL문장 전송 / 수신 
 *       PreparedStatement : BufferedReader / OutputStream  
 *                                |               | executeQuery,executeUpdate
 *                              ResultSet => 브라우저 전송 : List/VO
 *    ==========================================================
 *                 요청 ===> 요청값 받기 (request) => BufferedReader
 *     브라우저   <==========>  서버 (DAO,JSP)
 *                 응답 (HTML)
 *                 response : OutputStream 
 *     네트워크 : 서버 / 클라이언트 
 *              |
 *             오라클 / 웹 (톰캣)
 *     순서 => SQL문장 주력 
 *     
 *     
 *     1. JSP 
 *        ===
 *        1) 오라클로부터 데이터 읽기 
 *           <%
 *               => 
 *           %>
 *        2) 데이터를 HTML 출력 
 *     
 */
public class FoodDAO {
   private Connection conn; 
   private PreparedStatement ps;
   private DataBaseConnection dbConn=new DataBaseConnection();
   private static FoodDAO dao;
   public static FoodDAO newInstance()
   {
	   if(dao==null)
		   dao=new FoodDAO();
	   return dao;
   }
   
   public List<FoodVO> foodFindData(String addr,int page)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   try
	   {
		   conn=dbConn.getConnection();
		   String sql="SELECT fno,name,poster,num "
				     +"FROM (SELECT fno,name,poster,rownum as num "
				     +"FROM (SELECT fno,name,poster "
				     +"FROM food_house WHERE address LIKE '%'||?||'%')) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   int rowSize=12;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   // IN OUT 
		   ps.setString(1, addr);
		   ps.setInt(2, start);
		   ps.setInt(3, end);
		   // 결과값 받기 
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
		   System.out.println("===== foodFindData() 오류 발생 =====");
		   ex.printStackTrace();
	   }
	   finally
	   {
		   dbConn.disConnection(conn, ps);
	   }
	   return list;
   }
   public int foodFindTotalPage(String addr)
   {
	   int total=0;
	   try
	   {
		   conn=dbConn.getConnection();
		   String sql="SELECT CEIL(COUNT(*)/12.0) "
				     +"FROM food_house "
				     +"WHERE address LIKE '%'||?||'%'";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, addr);
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




