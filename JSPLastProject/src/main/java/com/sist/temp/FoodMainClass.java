package com.sist.temp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.vo.FoodVO;

public class FoodMainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        FoodMainClass mc=new FoodMainClass();
        mc.foodData();
	}
	public void foodData()
	{
		FoodDAO dao=new FoodDAO();
		
		try
		{
			int k=1;
			for(int i=1;i<=347;i++)
			{
				Document doc=Jsoup.connect("https://www.menupan.com/restaurant/bestrest/bestrest.asp?page="+i+"&trec=8674&pt=rt").get();
				Elements link=doc.select("ul.list p.listName a");
				for(int j=0;j<link.size();j++)
				{
				   try
				   {
					System.out.println(link.get(j).attr("href"));
					System.out.println("업체번호:"+ k++);
					String url="https://www.menupan.com"+link.get(j).attr("href");// 링크
					// 상세보기로 이동 
					// <li class="aa bb cc dd">
					Document doc2=Jsoup.connect(url).get();
					Element poster=doc2.selectFirst("div.areaThumbnail ul#id_restphoto_list_src img#restphoto_img_0");
					System.out.println(poster.attr("src"));
					Element name=doc2.selectFirst("div.areaBasic dd.name");
					System.out.println(name.text().substring(0,name.text().indexOf("[")));
					Element type=doc2.selectFirst("div.areaBasic dd.type");
					System.out.println(type.text());
					Element phone=doc2.selectFirst("div.areaBasic dd.tel1");
					System.out.println(phone.text());
					Element address=doc2.selectFirst("div.areaBasic dd.add1");
					System.out.println(address.text());
					Element score=doc2.selectFirst("div.areaBasic span.total");
					System.out.println(score.text());
					Element theme=doc2.selectFirst("div.areaBasic dd.Theme");
					System.out.println(theme.text());
					
					Element content=doc2.selectFirst("div.article div#info_ps_f");
					System.out.println(content.text());
					Element time=doc2.selectFirst("div.infoTable ul.tableTopA dd");
					System.out.println(time.text());
					Elements parking=doc2.select("div.infoTable ul.tableLR dt");
					//System.out.println(parking.text());
					String ppp="";
					for(int p=0;p<parking.size();p++)
					{
						String dt=parking.get(p).text();
						//System.out.println("dt="+dt);
						if(dt.trim().equals("주차"))
						{
							Element pp=doc2.select("div.infoTable ul.tableLR dd").get(p);
							System.out.println(pp.text());
							ppp=pp.text();
						}
					}
					String img="";
					for(int p=0;p<6;p++)
					{
						Element images=doc2.selectFirst("div.areaThumbnail img#restphoto_img_"+p);
						System.out.println(images.attr("src"));
						img+=images.attr("src")+"^";
					}
					img=img.substring(0,img.lastIndexOf("^"));
					System.out.println("==============================================");
					FoodVO vo=new FoodVO();
					vo.setName(name.text().substring(0,name.text().indexOf("[")));
					vo.setPhone(phone.text());
					vo.setType(type.text());
					vo.setAddress(address.text());
					vo.setTheme(theme.text());
					vo.setPoster(poster.attr("src"));
					vo.setContent(content.text());
					vo.setScore(Double.parseDouble(score.text()));
					vo.setParking(ppp);
					vo.setImages(img);
					vo.setTime(time.text());
					
					dao.foodInsert(vo);
				   }catch(Exception ex) {}
				}
			}
			System.out.println("저장 완료!!");
		}catch(Exception ex) {}
	}

}
