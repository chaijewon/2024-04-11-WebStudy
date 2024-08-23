package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
public class RecommandModel {
    @RequestMapping("recommand/food_recommand.do")
    public String food_recomand(HttpServletRequest request,HttpServletResponse response)
    {
    	request.setAttribute("main_jsp", "../recommand/food_recommand.jsp");
    	return "../main/main.jsp";
    }
    @RequestMapping("recommand/sub_menu.do")
    public String sub_menu(HttpServletRequest request,HttpServletResponse response)
    {
    	
    	String[] menu1={"휴식", "퇴근길", "휴가","여행",
    			        "거리" , "고백"
    	};
    	String[] menu2={"외로움", "기분전환", "슬픔" ,"이별","지침","설렘",
    			             "위로", "스트레스","짜증", "그리움" ,"우울" ,
    			             "불안","기쁨"
    	};
    	String[] menu3={"신나는","따뜻한", "편안한", "부드러운",  "달콤한",
    			        "시원한"
    	};
    	String[] menu4={"봄", "여름", "가을", "겨울", "맑은날", "추운날", "흐린날", 
    			              "비오는날", "더운날", "안개낀날", "눈오는날"};
    	
        String no=request.getParameter("no");
        List<String> list=new ArrayList<String>();
        
        if(no.equals("1"))
        {
        	list.clear();
        	for(String m:menu1)
        	{
        		list.add(m);
        	}
        }
        else if(no.equals("2"))
        {
        	list.clear();
        	for(String m:menu2)
        	{
        		list.add(m);
        	}
        }
        else if(no.equals("3"))
        {
        	list.clear();
        	for(String m:menu3)
        	{
        		list.add(m);
        	}
        }
        else if(no.equals("4"))
        {
        	list.clear();
        	for(String m:menu4)
        	{
        		list.add(m);
        	}
        }
        
        request.setAttribute("list", list);
        
    	return "../recommand/sub_menu.jsp";
    }
    @RequestMapping("recommand/recommand_result.do")
    public String recommand_result(HttpServletRequest request,HttpServletResponse response)
    {
    	String menu=request.getParameter("menu");
    	request.setAttribute("menu", menu);
    	return "../recommand/food_result.jsp";
    }
}









