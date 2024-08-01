package com.sist.model;
import java.awt.Robot;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.RowSet;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
public class BoardModel {
  @RequestMapping("board/list.do")
  public String board_list(HttpServletRequest request,HttpServletResponse response)
  {
	  // 사용자 요청 => 페이지 
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  int rowSize=10;
	  int start=(rowSize*curpage)-(rowSize-1);
	  int end=rowSize*curpage;
	  
	  Map map=new HashMap();
	  map.put("start", start);
	  map.put("end", end);
	  List<BoardVO> list=BoardDAO.boardListData(map);
	  // 총페이지 
	  int count=BoardDAO.boardRowCount();
	  int totalpage=(int)(Math.ceil(count/10.0));
	  count=count-((rowSize*curpage)-rowSize);
	  
	  request.setAttribute("list", list);
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("count", count);
	  // Controller에서 JSP로 전송 => request
	  return "list.jsp";
  }
  /*
   *    public void display(){}
   *    
   *    display(10 , 10)
   *    
   */
  // 메소드 => Controller => 모든 메소드에 매개변수 request,response
  @RequestMapping("board/insert.do")
  public String board_insert(HttpServletRequest request,HttpServletResponse response)
  {
	  return "insert.jsp";
  }
  @RequestMapping("board/insert_ok.do")
  public String board_insert_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String name=request.getParameter("name");
	  String subject=request.getParameter("subject");
	  String content=request.getParameter("content");
	  String pwd=request.getParameter("pwd");
	  
	  BoardVO vo=new BoardVO();
	  vo.setName(name);
	  vo.setSubject(subject);
	  vo.setContent(content);
	  vo.setPwd(pwd);
	  
	  // DAO연동 
	  BoardDAO.boardInsert(vo);
	  return "redirect:list.do"; // request초기화
  }
  @RequestMapping("board/detail.do") // => if을 추가 => 찾기 => 기능은 없다
  // 메소드 , 클래스 , 멤버변수 , 매개변수 , 생성자 => 모든 명칭을 자유롭게 사용이 가능
  public String board_detail(HttpServletRequest request,HttpServletResponse response)
  {
	  String no=request.getParameter("no");
	  BoardVO vo=BoardDAO.boardDetailData(Integer.parseInt(no));
	  request.setAttribute("vo", vo);
	  return "detail.jsp"; // forward
  }
  
  @RequestMapping("board/delete.do")
  public void board_delete(HttpServletRequest request,HttpServletResponse response)
  {
	  System.out.println("board_delete 수행");
	  String no=request.getParameter("no");
	  String pwd=request.getParameter("pwd");
	  boolean bCheck=BoardDAO.boardDelete(Integer.parseInt(no), pwd);
	  String result="";
	  if(bCheck==true)
	  {
		  result="yes";
	  }
	  else
	  {
		  result="no";
	  }
	  
	  try
	  {
		  PrintWriter out=response.getWriter();
		  out.write(result);
	  }catch(Exception ex) {}
  }
  
  @RequestMapping("board/update.do")
  public String board_update(HttpServletRequest request,HttpServletResponse response)
  {
	  String no=request.getParameter("no");
	  BoardVO vo=BoardDAO.boardUpdateData(Integer.parseInt(no));
	  request.setAttribute("vo", vo);
	  return "update.jsp";
  }
  
  @RequestMapping("board/update_ok.do")
  public void board_update_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  // void => 화면 이동이 없다 => javascript에서 처리 : Spring
	  String name=request.getParameter("name");
	  String subject=request.getParameter("subject");
	  String content=request.getParameter("content");
	  String pwd=request.getParameter("pwd");
	  String no=request.getParameter("no");
	  
	  BoardVO vo=new BoardVO();
	  vo.setName(name);
	  vo.setSubject(subject);
	  vo.setContent(content);
	  vo.setPwd(pwd);
	  vo.setNo(Integer.parseInt(no));
	  
	  // 데이터베이스 연동 
	  boolean bCheck=BoardDAO.boardUpdate(vo);
	  // 자바 스크립트에 데이터 전송 => yes/no
	  String result="";
	  if(bCheck==true)
	  {
		  result="yes";
	  }
	  else
	  {
		  result="no";
	  }
	  
	  try
	  {
		  PrintWriter out=response.getWriter();
		  out.write(result);
	  }catch(Exception ex) {}
  }
  
}
