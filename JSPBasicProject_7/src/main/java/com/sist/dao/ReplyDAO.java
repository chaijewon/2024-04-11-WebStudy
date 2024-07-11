package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import com.sist.database.DataBaseConnection;

public class ReplyDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static ReplyDAO dao;
	private DataBaseConnection dbConn=new DataBaseConnection();
	
	public static ReplyDAO newInstance()
	{
		if(dao==null)
			dao=new ReplyDAO();
		return dao;
	}
	/*
	try
	{
		conn=dbConn.getConnection();
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}
	finally
	{
		dbConn.disConnection(conn, ps);
	}
	*/
	// 1. 댓글 추가 
	public void replyInsert(ReplyVO vo)
	{
		try
		{
			conn=dbConn.getConnection();
			String sql="INSERT INTO food_reply VALUES("
					  +"fr_rno_seq.nextval,?,?,?,?,SYSDATE)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getFno());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getMsg());
			
			ps.executeUpdate();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbConn.disConnection(conn, ps);
		}
	}
	// 2. 데이터 읽기 
	public List<ReplyVO> replyListData(int fno)
	{
		List<ReplyVO> list=new ArrayList<ReplyVO>();
		try
		{
			conn=dbConn.getConnection();
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
	
}
