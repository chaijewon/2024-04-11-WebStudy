package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.*;
import com.sist.dao.*;
import com.sist.vo.*;
// 맛집 관련된 모든 기능 처리하는 클래스 
public class FoodModel {
   //1. 목록 출력 
   @RequestMapping("food/list.do") // 밑에 있는 클래스,변수,메소드를 찾아주는 역할 
   public String food_list(HttpServletRequest request,
		         HttpServletResponse response)
   {
	   
	   // 요청을 받는 경우 : request 
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1"; 
	   // 현재 페이지 지정 
	   int curpage=Integer.parseInt(page);
	   FoodDAO dao=FoodDAO.newInstance();
	   List<FoodVO> list=dao.foodListData(curpage);
	   int totalpage=dao.foodTotalPage();
	   // 응답 : response 
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;// 1 11 21...
	   /*
	    *   startPage = 1 
	    *      curpage = 1~10
	    *   endPage = 10
	    *      curpage = 1~10
	    */
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;// 10 20 30...
	   
	   if(endPage>totalpage)
		   endPage=totalpage; // 23
	   
	   // JSP에서 출력에 필요한 데이터를 전송 
	   request.setAttribute("list", list);
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   // include되는 파일을 전송 
	   request.setAttribute("main_jsp", "../food/list.jsp");
	   return "../main/main.jsp"; // jsp 파일 지정 => include가 된 경우 : main.jsp로 이동 
   }
   //2. 맛집 검색 
   //3. 맛집 예약 
   //4. 맛집 추천 
}
