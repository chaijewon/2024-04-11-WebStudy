package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class RecipeModel {
  // 리턴형 => String / void (Ajax):데이터만 전송 
  //         ====== 화면 변경
  // main.jsp , _ok.do => redirect
  @RequestMapping("recipe/recipe_list.do")
  public String recipe_list(HttpServletRequest request,HttpServletResponse response)
  {
	  // 어떤 데이터를  전송 
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  Map map=new HashMap();
	  map.put("start", (curpage*20)-19);
	  map.put("end", curpage*20);
	  List<RecipeVO> list=RecipeDAO.recipeListData(map);
	  int count=RecipeDAO.recipeTotalCount();
	  int totalpage=(int)(Math.ceil(count/20.0));
	  
	  // 블록별 
	  final int BLOCK=10;
	  int startPage=((curpage-1)/BLOCK*BLOCK)+1; // 1 11 21....
	  int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; // 10 20 30....
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // 데이터 전송 
	  request.setAttribute("recipeList", list);
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("startPage", startPage);
	  request.setAttribute("endPage", endPage);
	  request.setAttribute("count", count);
	  
	  request.setAttribute("main_jsp", "../recipe/recipe_list.jsp");
	  return "../main/main.jsp";
  }
}
