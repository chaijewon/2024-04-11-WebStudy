package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.dao.*;
import com.sist.vo.MusicVO;
public class MusicDAO {
  private static SqlSessionFactory ssf;
  static
  {
	  ssf=CreateSqlSessionFactory.getSsf();
  }
  /*
   *      <select id="musicListData" resultType="MusicVO">
		    SELECT mno,title,singer,poster,album,state,idcrement
		    FROM genie_music
		  </select>
		  <select id="musicFindData" resultType="MusicVO" parameterType="hashmap">
		    SELECT mno,title,singer,poster,album,state,idcrement
		    FROM genie_music
		    WHERE ${column} LIKE '%'||#{ss}||'%'
		  </select>
   */
  public static List<MusicVO> musicFindData(Map map)
  {
	  List<MusicVO> list=new ArrayList<MusicVO>();
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();//Connection주소 얻기
		  // MyBatis => DBCP (maxActive=8,maxIdle=8)
		  list=session.selectList("musicFindData",map);
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(session!=null)
			  session.close(); // POOL반환 => 재사용
	  }
	  return list;
  }
  public static List<MusicVO> musicListData()
  {
	  List<MusicVO> list=new ArrayList<MusicVO>();
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();//Connection주소 얻기
		  // MyBatis => DBCP (maxActive=8,maxIdle=8)
		  list=session.selectList("musicListData");
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(session!=null)
			  session.close(); // POOL반환 => 재사용
	  }
	  return list;
  }
  /*
   *   <select id="musicDetailData" resultType="String" parameterType="int">
	    SELECT key FROM genie_music
	    WHERE mno=#{mno}
	  </select>
   */
  public static String musicDetailData(int mno)
  {
	  String key="";
	  SqlSession session=null;
	  try
	  {
		  session=ssf.openSession();//Connection주소 얻기
		  // MyBatis => DBCP (maxActive=8,maxIdle=8)
		  key=session.selectOne("musicDetailData",mno);
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  if(session!=null)
			  session.close(); // POOL반환 => 재사용
	  }
	  return key;
  }
}
