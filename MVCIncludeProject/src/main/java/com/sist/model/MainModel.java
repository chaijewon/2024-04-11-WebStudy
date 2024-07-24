package com.sist.model;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.*;
import com.sist.dao.*;
public class MainModel {
	@RequestMapping("main/main.do")
    public String main_page(HttpServletRequest request,HttpServletResponse response)
    {
		String page=request.getParameter("page");
		  if(page==null)
			  page="1";
		  int curpage=Integer.parseInt(page);
		  FoodDAO dao=FoodDAO.newInstance();
		  List<FoodVO> list=dao.foodListData(curpage);
		  int totalpage=dao.foodTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
    	request.setAttribute("main_jsp", "../main/home.jsp");//include 
    	return "../main/main.jsp";
    }
}
