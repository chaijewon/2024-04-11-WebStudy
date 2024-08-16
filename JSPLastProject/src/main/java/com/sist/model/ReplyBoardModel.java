package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
public class ReplyBoardModel {
   @RequestMapping("replyboard/list.do")
   public String replyboard_list(HttpServletRequest request,HttpServletResponse response)
   {
	   request.setAttribute("main_jsp", "../replyboard/replyboard_list.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("replyboard/insert.do")
   public String replyboard_insert(HttpServletRequest request,HttpServletResponse response)
   {
	   request.setAttribute("main_jsp", "../replyboard/replyboard_insert.jsp");
	   return "../main/main.jsp"; // 새로운 페이지 제작 
   }
   @RequestMapping("replyboard/insert_ok.do")
   public String replyboard_insert_ok(HttpServletRequest request,HttpServletResponse response)
   {
	   
	   return "redirect:../replyboard/list.do";// 재호출 
   }
}
