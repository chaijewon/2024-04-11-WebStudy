package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class MainModel {
  @RequestMapping("main/main.do")
  public String main_main(HttpServletRequest request,HttpServletResponse response)
  {
	    List<FoodVO> hitList=FoodDAO.foodHitTopData();
	    List<FoodVO> likeList=FoodDAO.foodLikeTopData();
	    List<FoodVO> jjimList=FoodDAO.foodJjimTopData();
	    
	    request.setAttribute("hitList", hitList);
	    request.setAttribute("likeList", likeList);
	    request.setAttribute("jjimList", jjimList);
	    
	    request.setAttribute("main_jsp", "../main/home.jsp");
	    return "../main/main.jsp";
  }
}





