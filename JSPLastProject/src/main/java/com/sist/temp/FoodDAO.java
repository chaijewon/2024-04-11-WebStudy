package com.sist.temp;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
public class FoodDAO {
  private Connection conn;
  private PreparedStatement ps;
  private final String URL="jdbc:oracle:thin:@localhost:1521:xe";
  
  public void getConnection()
  {
	  try
	  {
		  conn=DriverManager.getConnection(URL,"hr","happy");
	  }catch(Exception ex) {}
  }
  public void distConnection()
  {
	  try
	  {
		  if(ps!=null) ps.close();
		  if(conn!=null) conn.close();
	  }catch(Exception ex) {}
  }
  /*
   *     FNO                                       NOT NULL NUMBER
		 NAME                                      NOT NULL VARCHAR2(500)
		 TYPE                                      NOT NULL VARCHAR2(100)
		 PHONE                                     NOT NULL VARCHAR2(50)
		 ADDRESS                                   NOT NULL VARCHAR2(300)
		 SCORE                                              NUMBER(2,1)
		 THEME                                              CLOB
		 POSTER                                    NOT NULL VARCHAR2(500)
		 IMAGES                                             CLOB
		 TIME                                               VARCHAR2(50)
		 PARKING                                            VARCHAR2(500)
		 CONTENT                                            CLOB
		 RDAYS                                              VARCHAR2(300)
		 JJIMCOUNT                                          NUMBER
		 LIKECOUNT                                          NUMBER
		 HIT                                                NUMBER
   */
  public void foodInsert(FoodVO vo)
  {
	  try
	  {
		  getConnection();
		  String sql="INSERT INTO project_food_house VALUES("
				   +"fh_fno_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,'',0,0,0)";
		  ps=conn.prepareStatement(sql);
		  ps.setString(1, vo.getName());
		  ps.setString(2, vo.getType());
		  ps.setString(3, vo.getPhone());
		  ps.setString(4, vo.getAddress());
		  ps.setDouble(5, vo.getScore());
		  ps.setString(6, vo.getTheme());
		  
		  ps.setString(7, vo.getPoster());
		  ps.setString(8, vo.getImages());
		  ps.setString(9, vo.getTime());
		  ps.setString(10, vo.getParking());
		  ps.setString(11, vo.getContent());
		  ps.executeUpdate();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  distConnection();
	  }
  }
}
