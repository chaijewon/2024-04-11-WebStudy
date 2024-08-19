package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import java.util.*;
import java.text.*;
public class ReserveModel {
  @RequestMapping("reserve/reserve_main.do") // if과 동일한 역할
  public String reserve_main(HttpServletRequest request,HttpServletResponse response)
  {
	  request.setAttribute("main_jsp", "../reserve/reserve_main.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("reserve/date_info.do")
  public String date_info(HttpServletRequest request,HttpServletResponse response)
  {
	  String strYear=request.getParameter("year");
	  String strMonth=request.getParameter("month");
	  String strDay="";
	  
	  Date date=new Date();
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
	  String today=sdf.format(date);
	  StringTokenizer st=new StringTokenizer(today,"-");
	  
	  String sy=st.nextToken();
	  String sm=st.nextToken();
	  strDay=st.nextToken();
	  
	  if(strYear==null)
	  {
		  strYear=sy;
	  }
	  if(strMonth==null)
	  {
		  strMonth=sm;
	  }
	
	  int year=Integer.parseInt(strYear);
	  int month=Integer.parseInt(strMonth);
	  int day=Integer.parseInt(strDay);
	  
	  // 요일 구하기 / 마지막 날 
	  Calendar cal=Calendar.getInstance();
	  cal.set(Calendar.YEAR, year);
	  cal.set(Calendar.MONTH, month-1);
	  cal.set(Calendar.DATE, 1);
	  
	  int week=cal.get(Calendar.DAY_OF_WEEK);
	  week=week-1;
	  
	  int lastday=cal.getActualMaximum(Calendar.DATE);
	  
	  request.setAttribute("year", year);
	  request.setAttribute("month", month);
	  request.setAttribute("day", day);
	  request.setAttribute("week", week);
	  request.setAttribute("lastday", lastday);
	  
	  String[] weeks={"일","월","화","수","목","금","토"};
	  request.setAttribute("weeks", weeks);
	  return "../reserve/date_info.jsp";
  }
  @RequestMapping("reserve/food_info.do")
  public String reserve_food_type(HttpServletRequest request,HttpServletResponse response)
  {
	  String type=request.getParameter("type");
	  if(type==null)
		  type="한식";
	  List<FoodVO> list=FoodDAO.foodTypeAllData(type);
	  request.setAttribute("fList", list);
	  return "../reserve/food_info.jsp";
  }
}
