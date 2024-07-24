package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sist.controller.RequestMapping;

public class SeoulModel {
	   @RequestMapping("seoul/weather.do")
	   // timeout(10000).validateTLSCertificates(false)
	   public String seoul_weather(HttpServletRequest request,HttpServletResponse response)
	   {
		   System.out.println(1);
		   String html="";
		   try
		   {
			   Document doc=Jsoup.connect("http://korean.visitseoul.net/weather").get();
			   Element elem=doc.selectFirst("section#content");
			   String data=elem.html();
			   data=data.replace("src=\"", "src=\"http://korean.visitseoul.net");
			   System.out.println(data);
			   html=data;
		   }catch(Exception ex) {ex.printStackTrace();}
		   request.setAttribute("html", html);
		   request.setAttribute("main_jsp", "../seoul/weather.jsp");
		   return "../main/main.jsp";
	   }
}
