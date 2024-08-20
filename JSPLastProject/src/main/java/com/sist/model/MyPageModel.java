package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.commons.CommonsModel;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;
public class MyPageModel {
   @RequestMapping("mypage/mypage_main.do")
   public String mypage_main(HttpServletRequest request,HttpServletResponse response)
   {
	   request.setAttribute("title", "마이페이지 홈");
	   request.setAttribute("mypage_jsp", "../mypage/mypage_home.jsp");
	   request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	   CommonsModel.footerPrint(request);
	   return "../main/main.jsp";
   }
   @RequestMapping("member/join_update.do")
   public String mypage_join_update(HttpServletRequest request,HttpServletResponse response)
   {
	   HttpSession session=request.getSession();
	   String id=(String)session.getAttribute("id");
	   MemberVO vo=MemberDAO.memberUpdateData(id);
	   
	   String phone=vo.getPhone();
	   phone=phone.substring(phone.indexOf(")")+1);
	   vo.setPhone(phone);
	   request.setAttribute("vo", vo);
	   request.setAttribute("title","회원 수정");
	   request.setAttribute("mypage_jsp", "../member/join_update.jsp");
	   request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	   CommonsModel.footerPrint(request);
	   return "../main/main.jsp";
   }
   @RequestMapping("member/join_update_ok.do")
   public String mypage_join_update_ok(HttpServletRequest request,HttpServletResponse response)
   {
	      try
		  {
			  request.setCharacterEncoding("UTF-8");
		  }catch(Exception ex) {}
		  String id=request.getParameter("id");
		  String pwd=request.getParameter("pwd");
		  String name=request.getParameter("name");
		  String sex=request.getParameter("sex");
		  String birthday=request.getParameter("birthday");
		  String post=request.getParameter("post");
		  String addr1=request.getParameter("addr1");
		  String addr2=request.getParameter("addr2");
		  String email=request.getParameter("email");
		  String content=request.getParameter("content");
		  String phone1=request.getParameter("phone1");
		  String phone2=request.getParameter("phone2");
		  
		  MemberVO vo=new MemberVO();
		  vo.setId(id);
		  vo.setPwd(pwd);
		  vo.setName(name);
		  vo.setSex(sex);
		  vo.setEmail(email);
		  vo.setBirthday(birthday);
		  vo.setPost(post);
		  vo.setAddr1(addr1);
		  vo.setAddr2(addr2);
		  vo.setContent(content);
		  vo.setPhone(phone1+")"+phone2);
		  
		  boolean bCheck=MemberDAO.memberUpdate(vo);
		  request.setAttribute("result", bCheck);
	   return "../member/join_update_ok.jsp";
   }
   @RequestMapping("mypage/mypage_jjim.do")
   public String mypage_jjim(HttpServletRequest request,HttpServletResponse response)
   {
	   HttpSession session=request.getSession();
	   String id=(String)session.getAttribute("id");
	   List<AllJjimVO> list=AllJjimDAO.JjimMyPageListData(id);
	   request.setAttribute("jjimCount", list.size());
	   request.setAttribute("title", "찜관리");
	   request.setAttribute("allList", list);
	   request.setAttribute("mypage_jsp", "../mypage/mypage_jjim.jsp");
	   request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("mypage/mypage_jjim_cancel.do")
   public String mypage_jjim_cancel(HttpServletRequest request,HttpServletResponse response)
   {
	   String jno=request.getParameter("jno");
	   // 데이터베이스 연동 
	   AllJjimDAO.JjjimCancel(Integer.parseInt(jno));
	   return "redirect:../mypage/mypage_jjim.do";
   }
   
}
