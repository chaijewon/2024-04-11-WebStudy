package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
public class FoodModel {
   @RequestMapping("food/list.do")
   public String food_list(HttpServletRequest request,HttpServletResponse response)
   {
	   // DB연동 => 출력할 데이터 전송 
	   int count=FoodDAO.foodListCount();
	   request.setAttribute("count", count);
	   
	   
	   request.setAttribute("main_jsp", "../food/list.jsp");
	   return "../main/main.jsp";
   }
}
