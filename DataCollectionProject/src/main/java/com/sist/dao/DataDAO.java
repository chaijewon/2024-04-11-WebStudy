package com.sist.dao;
import java.util.*;
import java.sql.*;
public class DataDAO {
   private Connection conn;
   private PreparedStatement ps;
   private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   private static DataDAO dao;
   // 드라이버 등록 
   public DataDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   }catch(Exception ex){}
   }
   // 연결 
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
   // 싱글턴 => DAO를 한번만 사용이 가능 (메모리 공간을 1개만 생성) = 재사용
   // 스프링에서는 기본 (싱글턴) => 필요시에는 여러개 객체 생성 => prototype
   public static DataDAO newInstance()
   {
	   if(dao==null)
		   dao=new DataDAO();
	   return dao;
   }
   // 데이터 수집 => insert
   public void movieInsert(MovieVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO project_movie VALUES(?,?,?,?,?,?,?,?,?,?,?,"
				     +"?,?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1,vo.getMno());
		   ps.setInt(2, vo.getCno());
		   ps.setString(3, vo.getTitle());
		   ps.setString(4, vo.getGrade());
		   ps.setString(5, vo.getReserve());
		   ps.setString(6, vo.getGenre());
		   ps.setString(7, vo.getTime());
		   ps.setString(8, vo.getRegdate());
		   ps.setString(9, vo.getDirector());
		   ps.setString(10, vo.getActor());
		   ps.setString(11, vo.getShowUser());
		   ps.setString(12, vo.getPoster());
		   ps.setString(13, vo.getStory());
		   ps.setString(14, vo.getKey());
		   ps.setInt(15, 0);
		   ps.setDouble(16, vo.getScore());
		   // 실행요청 
		   ps.executeUpdate();// Commit()
		   /*
		    *   => 크롤링 / 오라클 열기(닫기) 
		    *      ==== 약간 속도를 줄인다 (Thread.sleep())
		    *      
		    */
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
   }
   
   // 카테고리 저장 
   public void foodCategoryInsert(FoodCategoryVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO project_food_category VALUES("
				     +"?,?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getCno());
		   ps.setString(2, vo.getTitle());
		   ps.setString(3, vo.getSubject());
		   ps.setString(4, vo.getPoster());
		   ps.setString(5, vo.getLink());
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
   // 카테고리별 맛집 저장
  
   public void foodHouseInsert(FoodHouseVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO project_food_house VALUES("
				     +"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getNo());
		   ps.setInt(2, vo.getCno());
		   ps.setString(3, vo.getPoster());
		   ps.setString(4, vo.getName());
		   ps.setDouble(5, vo.getScore());
		   ps.setString(6, vo.getAddress());
		   ps.setString(7, vo.getTel());
		   ps.setString(8, vo.getType());
		   ps.setString(9, vo.getPrice());
		   ps.setString(10, vo.getParking());
		   ps.setString(11, vo.getTime());
		   ps.setString(12, vo.getMenu());
		   ps.setInt(13, vo.getGood());
		   ps.setInt(14, vo.getSoso());
		   ps.setInt(15, vo.getBad());
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
   // 지역별 맛집 => 캡쳐 
   
   public void foodLocationInsert(FoodLocationVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO food_location VALUES("
				     +"?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getNo());
		   ps.setString(2, vo.getPoster());
		   ps.setString(3, vo.getName());
		   ps.setDouble(4, vo.getScore());
		   ps.setString(5, vo.getAddress());
		   ps.setString(6, vo.getTel());
		   ps.setString(7, vo.getType());
		   ps.setString(8, vo.getPrice());
		   ps.setString(9, vo.getParking());
		   ps.setString(10, vo.getTime());
		   ps.setString(11, vo.getMenu());
		   ps.setInt(12, vo.getGood());
		   ps.setInt(13, vo.getSoso());
		   ps.setInt(14, vo.getBad());
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
   
   public List<FoodCategoryVO> foodCategoryInfoData()
   {
	   // link , cno
	   List<FoodCategoryVO> list=new ArrayList<FoodCategoryVO>();
	   try
	   {
		   getConnection();
		   String sql="SELECT cno,link FROM project_food_category "
				     +"ORDER BY 1";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FoodCategoryVO vo=new FoodCategoryVO();
			   vo.setCno(rs.getInt(1));
			   vo.setLink(rs.getString(2));
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
   // ==> 영화 => 맛집 => 레시피 => 명소 ,호텔 , 자연(관광) => 일정(코스) (추천)
   // 명소 
   /*
    *  NO      NOT NULL NUMBER         
	TITLE   NOT NULL VARCHAR2(200)  
	POSTER  NOT NULL VARCHAR2(500)  
	MSG     NOT NULL VARCHAR2(4000) 
	ADDRESS NOT NULL VARCHAR2(300)   
    */
   public void seoulLocationInsert(SeoulLocationVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO project_seoul_location VALUES((SELECT NVL(MAX(no)+1,1) FROM project_seoul_location),?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   //ps.setInt(1, vo.getNo());
		   ps.setString(1, vo.getTitle());
		   ps.setString(2, vo.getPoster());
		   ps.setString(3, vo.getMsg());
		   ps.setString(4, vo.getAddress());
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
   // 호텔
   /*
    *  NO      NOT NULL NUMBER         
	NAME    NOT NULL VARCHAR2(100)  
	SCORE            NUMBER(2,1)    
	ADDRESS NOT NULL VARCHAR2(300)  
	POSTER  NOT NULL VARCHAR2(4000) 
    */
   public void seoulHotelInsert(SeoulHotelVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO seoul_hotel VALUES(?,?,?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getNo());
		   ps.setString(2, vo.getName());
		   ps.setDouble(3, vo.getScore());
		   ps.setString(4, vo.getAddress());
		   ps.setString(5, vo.getPoster());
		   ps.setString(6, vo.getImages());
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
   // 자연 
  
   public void seoulNatureInsert(SeoulNatureVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO project_seoul_shop VALUES((SELECT NVL(MAX(no)+1,1) FROM project_seoul_shop),?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   //ps.setInt(1, vo.getNo());
		   ps.setString(1, vo.getTitle());
		   ps.setString(2, vo.getPoster());
		   ps.setString(3, vo.getMsg());
		   ps.setString(4, vo.getAddress());
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
   // 레시피 
   /*
    *   NO     NOT NULL NUMBER         
		TITLE  NOT NULL VARCHAR2(1000) 
		POSTER NOT NULL VARCHAR2(260)  
		CHEF   NOT NULL VARCHAR2(200)  
		LINK            VARCHAR2(260)  
		HIT             NUMBER 
    */
   public void recipeInsert(RecipeVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO recipe VALUES((SELECT NVL(MAX(no)+1,1) FROM recipe),?,?,?,?,0)";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, vo.getTitle());
		   ps.setString(2, vo.getPoster());
		   ps.setString(3, vo.getChef());
		   ps.setString(4, vo.getLink());
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
   // 레시피 상세
   // 쉐프  1,3,7,2
   public void chefInsert(ChefVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO chef VALUES(?,?,?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, vo.getChef());
		   ps.setString(2, vo.getPoster());
		   ps.setString(3, vo.getMem_cont1());
		   ps.setString(4, vo.getMem_cont3());
		   ps.setString(5, vo.getMem_cont7());
		   ps.setString(6, vo.getMem_cont2());
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
   /*
    *   NO           NOT NULL NUMBER         
POSTER       NOT NULL VARCHAR2(4000) 
TITLE        NOT NULL VARCHAR2(1000) 
CHEF         NOT NULL VARCHAR2(1000) 
CHEF_POSTER  NOT NULL VARCHAR2(1000) 
CHEF_PROFILE NOT NULL VARCHAR2(1000) 
INFO1        NOT NULL VARCHAR2(100)  
INFO2        NOT NULL VARCHAR2(100)  
INFO3        NOT NULL VARCHAR2(100)  
CONTENT               VARCHAR2(4000) 
FOODMAKE     NOT NULL CLOB           
DATA                  CLOB  
    */
   public void recipeDetailInsert(RecipeDetailVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO recipeDetail VALUES("
				   +"?,?,?,?,?,?,?,?,?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getNo());
		   ps.setString(2, vo.getPoster());
		   ps.setString(3, vo.getTitle());
		   ps.setString(4, vo.getChef());
		   ps.setString(5, vo.getChef_poster());
		   ps.setString(6, vo.getChef_profile());
		   ps.setString(7, vo.getInfo1());
		   ps.setString(8, vo.getInfo2());
		   ps.setString(9, vo.getInfo3());
		   ps.setString(10, vo.getContent());
		   ps.setString(11, vo.getFoodmake());
		   ps.setString(12, vo.getData());
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
   public List<RecipeVO> recipeData()
   {
	   List<RecipeVO> list=new ArrayList<RecipeVO>();
	   try
	   {
		   getConnection();
		   String sql="SELECT no,link FROM recipe ORDER BY no ASC";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   RecipeVO vo=new RecipeVO();
			   vo.setNo(rs.getInt(1));
			   vo.setLink(rs.getString(2));
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






