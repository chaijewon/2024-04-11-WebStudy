package com.sist.model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.*;
import com.sist.dao.*;
public class FoodModel {
   @RequestMapping("food/detail.do")
   public String food_detail(HttpServletRequest request,HttpServletResponse response)
   {
	   String fno=request.getParameter("fno");
	   FoodDAO dao=FoodDAO.newInstance();
	   FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../food/detail.jsp");
	   return "../main/main.jsp";
   }
}
