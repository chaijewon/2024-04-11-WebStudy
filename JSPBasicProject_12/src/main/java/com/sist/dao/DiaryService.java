package com.sist.dao;
/*
 *   table => VO , DAO 
 *   ===== table 여러개 혼합 => Service
 *                          ======== board/reply
 *                          ======== food / jjim / like
 *    C/S => 네트워크 통신 
 *                request(JSP) => URL           
 *    브라우저   ================> 서버
 *            <================
 *              response(HTML) => 메모리를 읽어 간다 
 *              
 *                 SQL문장 PreparedStatement
 *    클라이언트  ===============> 오라클 
 *             <===============
 *               실제 저장된 데이터  PreparedStatement => OutputStream , BufferedReader
 */
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class DiaryService {
   private Connection conn;
   private PreparedStatement ps;
   private static DiaryService dao;
   
   public static DiaryService newInstance()
   {
	   if(dao==null)
		   dao=new DiaryService();
	   return dao;
   }
   // 미리 톰캣에 의해 생성된 Connection 주소를 얻어온다 
   /*
    *     => JNDI => new InitialContext()
    *     =================java://comp/env==========================
    *       ====================================
    *        new Connection() false jdbc/oracle 
    *                         =====
    *                          true
    *       ====================================
    *        new Connection() false jdbc/oracle
    *       ====================================
    *        
    *     ===========================================
    */
   public void getConnection()
   {
	   try
	   {
		   Context init=new InitialContext();
		   Context c=(Context)init.lookup("java://comp/env");
		   DataSource ds=(DataSource)c.lookup("jdbc/oracle");
		   conn=ds.getConnection();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   // 다시 Connection 관리영역(POOL) 으로 반환 => 연결에 소모되는 시간을 절약하기 위해 
   // Connection을 일정하게 관리 할 수 있다 
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex) {}
   }
   // 1. 로그인 => 세션 사용 => 사용자 정보 저장 
   // JSP => 변경이 많다 (일반 HTML => AJAX => VUE => REACT = REDUX = REACT-QUERY)
   // 자바 => 변경이 없다 (스프링) 
   public MemberVO isLogin(String id,String pwd) // ajax
   {
	   MemberVO vo=new MemberVO();
	   try
	   {
		   getConnection();
		   // 1. 아이디 존재여부 확인
		   String sql="SELECT COUNT(*) FROM member "
				     +"WHERE id=?";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, id); // 'hong'
		   // 실행 결과 읽기 
		   ResultSet rs=ps.executeQuery();
		   rs.next(); // 데이터 출력위치로 커서 => 이동 
		   int count=rs.getInt(1);
		   rs.close();
		   
		   if(count==0)//ID가 없는 상태 
		   {
			   vo.setMsg("NOID");
		   }
		   else // ID가 존재하는 상태 
		   {
		      // 2. 비밀번호 확인
			  sql="SELECT pwd,name,sex FROM member "
				 +"WHERE id=?";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, id);
			  
			  rs=ps.executeQuery();
			  rs.next();
			  String db_pwd=rs.getString(1);
			  String name=rs.getString(2);
			  String sex=rs.getString(3);
			  rs.close();
			  
			  if(db_pwd.equals(pwd))// 로그인 
			  {
				  vo.setId(id);
				  vo.setName(name);
				  vo.setSex(sex);
				  vo.setMsg("OK");
			  }
			  else //비밀번호가 틀린 상태 
			  {
				  vo.setMsg("NOPWD");
			  }
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();// 반환 (재사용)
	   }
	   return vo;
   }
   /*
    *   로그인 => 매개변수 => id,pwd => 사용자가 입력하거나 클릭 
    *                    ======== 사용자 전송 
    *           리턴형 => 결과값 
    *           ==== 경우의 수 => 경우의 수 1=> List/VO/int
    *                           경우의 수 2 => boolean 
    *                           경우의 수 여러개 => String 
    */
   // 2. 일정관리 : 아이디별로 
   // 2-1 일정 등록 
   public void diaryInsert(DiaryVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO diary VALUES("
				     +"diary_no_seq.nextval,?,?,?,?,?,?,SYSDATE)";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, vo.getId());
		   ps.setString(2, vo.getSubject());
		   ps.setString(3, vo.getMsg());
		   ps.setInt(4, vo.getYear());
		   ps.setInt(5, vo.getMonth());
		   ps.setInt(6, vo.getDay());
		   ps.executeUpdate();
	   }catch(Exception ex) 
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
   }
   // 2-2 일정 출력 
   // 2-3 달력에 일정 표시
   public int diaryCheck(String id,int year,int month,int day)
   {
	   int bCheck=0;
	   try
	   {
		   getConnection();
		   String sql="SELECT COUNT(*) FROM diary "
				     +"WHERE id=? AND year=? AND month=? AND day=?";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, id);
		   ps.setInt(2, year);
		   ps.setInt(3, month);
		   ps.setInt(4, day);
		   
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   bCheck=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return bCheck;
   }
   // 2-4 일정 수정 
   // 2-5 일정 취소
   // 3. 장바구니 : 세션 => 제공하는 메소드 정리 
   // 3-1 상품 출력 
   // 3-2 장바구니 등록 => 세션 처리 
   // 3-3 장바구니 상품 취소 
   // 3-4 구매 => 데이터베이스에 저장 
}
