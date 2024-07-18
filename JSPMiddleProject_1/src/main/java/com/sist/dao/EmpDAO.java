package com.sist.dao;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;
public class EmpDAO {
	   private Connection conn;
	   // AWS => 프로젝트 => 실행 => 어떤 위치든 상관없이 실행이 가능 
	   private PreparedStatement ps; 
	   private static EmpDAO dao;
	   
	   // 싱글턴 
	   public static EmpDAO newInstance()
	   {
		   if(dao==null)
			   dao=new EmpDAO();
		   return dao;
	   }
	   /*
	    *   1.DBCP 
	    *     미리 일정 갯수의 Connection을 만들어서 POOL에 저장 
	    *     ========================================= 저장하는 갯수 : maxIdle 
	    *      => 인원 초과 => 최대 Connection => maxActive
	    *     사용자가 요청을 하면 Conection 주소를 제공해서 사용이 가능하게 만든다 
	    *     사용이 종료가 되면 POOL안으로 Connection을 반환 
	    *                            ========== 재사용이 가능 
	    *     1. 데이터베이스 연결에 소모되는 시간을 줄일 수 있다 
	    *                      ========== 연결하는 시간 오래 걸린다 
	    *     2. Connection을 일정 갯수로 관리 할 수 있다
	    *     3. 동시 접속자가 많아도 쉽게 다운되지 않는다 
	    *     
	    *     동작 : 톰캣이 Connection을 POOL에저장 
	    *          ==========================
	    *          1. POOL안에서 Connection 객체를 가지고 온다 
	    *          2. Connection 객체를 이용해서 DB작업을 수행
	    *          3. 수행이 종료되는 다시 재사용을 위해서 반환한다
	    *          
	    *     POOL 
	    *     ============================
	    *      => 주소 : java://conf/env 
	    *              ====================================
	    *              jdbc/oracle new Connection() false
	    *              jdbc/oracle new Connection() false
	    *              jdbc/oracle new Connection() false
	    *              jdbc/oracle new Connection() false
	    *              jdbc/oracle new Connection() false
	    *                   => 사용 => true / 반환 => false
	    *     =============================================
	    *     
	    *     JDBC => DBCP => ORM (MyBatis) => DataSet (JPA)
	    *            ======
	    *            1. WEB에서만 사용이 가능
	    */
	   public void getConnection()
	   {
		   // 미리 오라클 연결해서 저장된 Connection의 주소를 얻어 온다 
		   try
		   {
			   // 1. POOL에 연결 => JNDI (Java Naming Directory Interface)
			   // 이름형식으로 탐색기를 제작 
			   Context init=new InitialContext();// 탐색기 열기 
			   Context c=(Context)init.lookup("java://comp//env");// C드라이버에 접근 
			   DataSource ds=(DataSource)c.lookup("jdbc/oracle");// 원하는 폴더선택 
			   conn=ds.getConnection();// 파일을 읽기 (주소 얻기)
		   }catch(Exception ex) 
		   {
			   ex.printStackTrace();
		   }
	   }
	   public void disConnection()
	   {
		   // 반환 => 사용후에 
		   try
		   {
			   if(ps!=null) ps.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex) {}
	   }
	   
	   public List<EmpVO> empListData()
	   {
		   List<EmpVO> list=new ArrayList<EmpVO>();
		   try
		   {
			   getConnection();
			   String sql="SELECT empno,ename,job,sal,hiredate "
					     +"FROM emp";
			   ps=conn.prepareStatement(sql);
			   ResultSet rs=ps.executeQuery();
			   while(rs.next())
			   {
				   EmpVO vo=new EmpVO();
				   vo.setEmpno(rs.getInt(1));
				   vo.setEname(rs.getString(2));
				   vo.setJob(rs.getString(3));
				   vo.setSal(rs.getInt(4));
				   vo.setHiredate(rs.getDate(5));
				   
				   list.add(vo);
			   }
			   rs.close();
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
		   return list;
	   }
}
