package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MainClass mc=new MainClass();
        mc.genieData();
	}
	public void genieData()
	{
		try
		{
			int k=1;
			for(int i=1;i<=4;i++)
			{
				Document doc=Jsoup.connect("https://www.genie.co.kr/chart/top200?ditc=D&ymd=20240627&hh=15&rtm=Y&pg="+i).get();
				Elements title=doc.select("table.list-wrap td.info a.title");
				Elements singer=doc.select("table.list-wrap td.info a.artist");
				Elements album=doc.select("table.list-wrap td.info a.albumtitle");
				Elements poster=doc.select("table.list-wrap a.cover img");
				Elements etc=doc.select("table.list-wrap span.rank");
				for(int j=0;j<title.size();j++)
				{
					System.out.println(k);
					System.out.println(title.get(j).text());
					System.out.println(singer.get(j).text());
					System.out.println(album.get(j).text());
					System.out.println(poster.get(j).attr("src"));
					//System.out.println(etc.get(j).text());
					String s=etc.get(j).text();
					String id="";
					String state="";
					if(s.equals("유지"))
					{
						id="0";
						state="유지";
					}
					else
					{
						id=s.replaceAll("[^0-9]", "");
						state=s.replaceAll("[^가-힣]", "");
					}
					System.out.println("상태:"+state);
					System.out.println("등폭:"+id);
					System.out.println("=======================================");
				    k++;
				}
			}
		}catch(Exception ex){}
	}

}
