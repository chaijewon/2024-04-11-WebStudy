package com.sist.model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.*;
public class MainModel {
	@RequestMapping("main/main.do")
    public String main_page(HttpServletRequest request,HttpServletResponse response)
    {
    	request.setAttribute("main_jsp", "../main/home.jsp");//include 
    	return "../main/main.jsp";
    }
}
