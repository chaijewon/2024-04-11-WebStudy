package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class NoticeModel {
   private String[] types={"","일반공지","이벤트공지","맛집공지","여행공지"};
   @RequestMapping("notice/list.do")
   public String notice_list(HttpServletRequest request,HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=15;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end", end);
	   
	   List<NoticeVO> list=NoticeDAO.noticeListData(map);
	   for(NoticeVO vo:list)
	   {
		   vo.setNotice_type(types[vo.getType()]);
	   }
	   int count=NoticeDAO.noticeRowCount();
	   int totalpage=(int)(Math.ceil(count/15.0));
	   count=count-((curpage*rowSize)-rowSize);
	   
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("count", count);
	   request.setAttribute("noticeList", list);
	   request.setAttribute("main_jsp", "../notice/list.jsp");
	   return "../main/main.jsp";
   }
}
