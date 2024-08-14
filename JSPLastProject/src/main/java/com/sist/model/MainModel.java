package com.sist.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.commons.CommonsModel;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import io.netty.util.internal.logging.CommonsLoggerFactory;
public class MainModel {
  @RequestMapping("main/main.do")
  public String main_main(HttpServletRequest request,HttpServletResponse response)
  {
	    Cookie[] cookies=request.getCookies();
	    List<FoodVO> cookieList=new ArrayList<FoodVO>();
	    // Cookie cookie=new Cookie("food_"+fno, fno);
	    if(cookies!=null)
	    {
	    	for(int i=cookies.length-1;i>=0;i--)
	    	{
	    		if(cookies[i].getName().startsWith("food_"))
	    		{
	    			String fno=cookies[i].getValue();
	    			FoodVO vo=FoodDAO.foodDetailData(Integer.parseInt(fno));
	    			cookieList.add(vo);
	    		}
	    	}
	    }
	    
	    List<FoodVO> hitList=FoodDAO.foodHitTopData();
	    List<FoodVO> likeList=FoodDAO.foodLikeTopData();
	    List<FoodVO> jjimList=FoodDAO.foodJjimTopData();
	    /*
	     *   Footer에 공지사항 출력 
	     */
	    
	    CommonsModel.footerPrint(request);
	    request.setAttribute("cookieList", cookieList);
	    request.setAttribute("hitList", hitList);
	    request.setAttribute("likeList", likeList);
	    request.setAttribute("jjimList", jjimList);
	    
	    request.setAttribute("main_jsp", "../main/home.jsp");
	    return "../main/main.jsp";
  }
  
}





