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
   private String[] mode={"","한식","중식","양식","일식"};
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
   // 종류별 분리 50~60 : SELECT 
   /*
    *  INSERT : 게시판 글쓰기 , 댓글 , 회원가입 , 장바구니 , 예약 
    *  UPDATE : 게시판 수정   댓글 수정  회원가입 장바구니 수정 예약수정 
    *  DELETE : 게시판 삭제   댓글 삭제   회원탈퇴 장바구니 취소 예약취소 
    *  나머지 : SELECT
    */
   public List<FoodVO> foodListData(int type,int page)
   {
	   List<FoodVO> list=new ArrayList<FoodVO>();
	   
	   try
	   {
		   conn=dbConn.getConnection();
		   String sql="SELECT fno,name,poster,num "
				     +"FROM (SELECT fno,name,poster,rownum as num "
				     +"FROM (SELECT fno,name,poster "
				     +"FROM food_house WHERE type LIKE '%'||?||'%')) "
				     +"WHERE num BETWEEN ? AND ?";
		   /*
		    *   String sql="SELECT fno,name,poster,num "
				     +"FROM (SELECT fno,name,poster,rownum as num "
				     +"FROM (SELECT fno,name,poster "
				     +"FROM food_house WHERE REGEXP_LIKE(type,?))) "
				     +"WHERE num BETWEEN ? AND ?";
		    */
		   // rownum은 Top-N => 처음부터 , 중간은 자르지 못한다 => 인라인뷰 
		   ps=conn.prepareStatement(sql);
		   int rowSize=12;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   
		   // ?에 값을 채운다 => 실행
		   ps.setString(1, mode[type]);
		   ps.setInt(2, start);
		   ps.setInt(3, end);
		   // => 전체 / 베스트 / 특가 / 신상품 => 각각 테이블이 만들어져 있다 
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
   public int foodListTotalPage(int type)
   {
	   int total=0;
	   try
	   {
		   conn=dbConn.getConnection();
		   String sql="SELECT CEIL(COUNT(*)/12.0) "
				     +"FROM food_house "
				     +"WHERE type LIKE '%'||?||'%'";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, mode[type]);
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




