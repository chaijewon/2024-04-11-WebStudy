package com.sist.dao;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PrintTimer {
	   
	   public static void main(String[] args) {
	      ScheduledJob job = new ScheduledJob();
	      Timer jobScheduler = new Timer();
	      jobScheduler.scheduleAtFixedRate(job, 1000, 5000);
	      try {
	         Thread.sleep(20000);
	      } catch(InterruptedException ex) {
	         //
	      }
	      jobScheduler.cancel();
	   }
	}

	class ScheduledJob extends TimerTask {
	   
	   public void run() {
	      try
	      {
	    	  Document doc=Jsoup.connect("https://www.kobis.or.kr/kobis/business/main/searchMainDailyBoxOffice.do").get();
	    	  String json=doc.toString();
	    	  json=json.substring(json.indexOf("["),json.lastIndexOf("]")+1);
	    	  //System.out.println(json);
	    	  JSONParser jp=new JSONParser();
	    	  JSONArray arr=(JSONArray)jp.parse(json);
	    	  for(int i=0;i<arr.size();i++)
	    	  {
	    		  JSONObject obj=(JSONObject)arr.get(i);
	    		  String name=(String)obj.get("movieNm");
	    		  System.out.println((i+1)+"."+name);
	    	  }
	    	  System.out.println("============================");
	      }catch(Exception ex){}
	   }
	}