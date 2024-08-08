package com.sist.model;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
public class DataBoardModel {
  @RequestMapping("databoard/insert.do")
  public String databoard_insert(HttpServletRequest request,HttpServletResponse response)
  {
	  request.setAttribute("main_jsp", "../databoard/insert.jsp");
	  return "../main/main.jsp";
  }
  
  @RequestMapping("databoard/insert_ok.do")
  public String databoard_insert_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
		  String path="c:\\project_upload";
		  String enctype="UTF-8";// 한글 파일명 
		  int max_size=1024*1024*100;
		  MultipartRequest mr=
				  new MultipartRequest(request, path,max_size,enctype,
						  new DefaultFileRenamePolicy());
		  // 자동 변경 => a.jpg , a1.jpg
		  String name=mr.getParameter("name");
		  String subject=mr.getParameter("subject");
		  String content=mr.getParameter("content");
		  String pwd=mr.getParameter("pwd");
		  String filename=mr.getFilesystemName("upload");
		  // a.jpg
		  // a.jpg => a1.jpg
		  DataBoardVO vo=new DataBoardVO();
		  vo.setName(name);
		  vo.setSubject(subject);
		  vo.setContent(content);
		  vo.setPwd(pwd);
		  
		  if(filename==null) // 업로드가 없는 상태 
		  {
			  vo.setFilename("");
			  vo.setFilesize(0);
		  }
		  else // 업로드가 된 상태 
		  {
			  File file=new File(path+"\\"+filename);
			  vo.setFilename(filename);
			  vo.setFilesize((int)file.length()); // int => 2byte
		  }
		  
		  DataBoardDAO.databoardInsert(vo);
	  }catch(Exception ex){}
	  return "redirect:../databoard/list.do";
  }
  @RequestMapping("databoard/list.do")
  public String databoard_list(HttpServletRequest request,HttpServletResponse response)
  {
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  
	  int curpage=Integer.parseInt(page);
	  Map map=new HashMap();
	  map.put("start", (curpage*10)-9);
	  map.put("end", curpage*10);
	  
	  List<DataBoardVO> list=DataBoardDAO.databoardListData(map);
	  int count=DataBoardDAO.databoardRowCount();
	  int totalpage=(int)(Math.ceil(count/10.0));
	  count=count-((curpage*10)-10);
	  
	  request.setAttribute("list", list);
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("count", count);
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	  Date date=new Date();
	  String today=sdf.format(date);
	  // new SimpleDateFormat("yyyy-MM-dd").format(new Date())
	  request.setAttribute("today", today);
	  request.setAttribute("main_jsp", "../databoard/list.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("databoard/detail.do")
  public String databoard_detail(HttpServletRequest request,HttpServletResponse response)
  {
	  // ajax (void) , _ok : redirect:
	  String no=request.getParameter("no");
	  DataBoardVO vo=DataBoardDAO.databoardDetailData(Integer.parseInt(no));
	  request.setAttribute("vo", vo);
	  request.setAttribute("main_jsp", "../databoard/detail.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("databoard/download.do")
  public void databoard_download(HttpServletRequest request,HttpServletResponse response)
  {
	  try
	  {
		  String fn=request.getParameter("fn");
		  File file=new File("c:\\project_upload\\"+fn);
		  // header => 전송 : 파일명 , 파일 크기 
		  response.setHeader("Content-Disposition", "attachment;filename="
				  +URLEncoder.encode(fn, "UTF-8"));
		  response.setContentLength((int)file.length());
		  
		  BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
		  // 서버에서 파일 읽기 
		  BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
		  // 다운로드를 요청한 클라이언트
		  byte[] buffer=new byte[1024];// 파일 저장 공간 
		  int i=0;// 읽은 바이트수 
		  while((i=bis.read(buffer, 0, 1024))!=-1)
		  {
			  bos.write(buffer, 0, i);
		  }
		  bis.close();
		  bos.close();
		  
	  }catch(Exception ex){}
  }
  // Model => 사용자 요청 처리 =>: 결과값 / 화면 이동 
  @RequestMapping("databoard/delete.do") // => if문 동일 
  public void databoard_delete(HttpServletRequest request,HttpServletResponse response)
  {
	  // {"no":no,"pwd":pwd} => delete.do?no=1&pwd=1234
	  String no=request.getParameter("no");
	  String pwd=request.getParameter("pwd");
	  System.out.println("no="+no+",pwd="+pwd);
	  // 데이터베이스 연동 
	  DataBoardVO vo=DataBoardDAO.databoardFileInfoData(Integer.parseInt(no));
	  String result=DataBoardDAO.databoardDelete(Integer.parseInt(no), pwd);
	  // 파일 삭제 
	  try
	  {
		  if(vo.getFilesize()>0)
		  {
			  File file=new File("c:\\project_upload\\"+vo.getFilename());
			  file.delete();
		  }
	  }catch(Exception ex) {}
	  // 결과값을 받아서 Ajax로 전송 
	  try
	  {
		  PrintWriter out=response.getWriter();
		  out.write(result);
	  }catch(Exception ex) {}
  }
  
}
