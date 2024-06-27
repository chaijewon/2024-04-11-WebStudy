package com.sist.dao;
import java.util.*;
import java.sql.*;
public class MusicDAO {
   private Connection conn;
   private PreparedStatement ps;
   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   private static MusicDAO dao;
   
   public MusicDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   }catch(Exception ex) {}
   }
   
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
	   }catch(Exception ex) {}
   }
   
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex) {}
   }
   
   public static MusicDAO newInstance()
   {
	   if(dao==null)
		   dao=new MusicDAO();
	   return dao;
   }
   /*
    *  MNO                                       NOT NULL NUMBER
    TITLE                                     NOT NULL VARCHAR2(2000)
    SINGER                                    NOT NULL VARCHAR2(2000)
    ALBUM                                              VARCHAR2(2000)
    POSTER                                    NOT NULL VARCHAR2(300)
    STATE                                              CHAR(12)
    IDCREMENT                                          NUMBER
    */
   ///////////////////////////// MyBatis 여기까지는 지원
   public void musicInsert(MusicVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO genie_music VALUES("
				     +"gm_mno_seq.nextval,?,?,?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, vo.getTitle());
		   ps.setString(2, vo.getSinger());
		   ps.setString(3, vo.getAlbum());
		   ps.setString(4, vo.getPoster());
		   ps.setString(5, vo.getState());
		   ps.setInt(6, vo.getIdcrement());
		   
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
   
}




