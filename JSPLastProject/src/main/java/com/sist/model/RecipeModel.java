package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
/*
 *    1. Back-End 
 *        => MVC (동작 순서) 
 *                 .do                                                          request
 *           JSP ========== Controller ========= Model =============== Controller ==== JSP
 *                               |                @RequestMapping()         |
 *                               --------------------------------------------
 *                                                   |
 *                                                 Spring
 *        => SQL문장 => MyBatis연결 
 *                                                   
 *                                                   
 *    2. Front-End
 *        => JavaScript : 문법 
 *        => Jquery 사용법 / Ajax 
 *        ======================
 */
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
  // 
  @RequestMapping("recipe/recipe_detail.do")
  public String recipe_detail(HttpServletRequest request,HttpServletResponse response)
  {
	  // 사용자의 요청 정보 받기 => ?
	  String no=request.getParameter("no");
	  // 데이터 베이스 연동 
	  RecipeDetailVO vo=RecipeDAO.recipeDetailData(Integer.parseInt(no));
	  List<String> sList=new ArrayList<String>();
	  List<String> iList=new ArrayList<String>();
	  
	  String[] temp=vo.getFoodmake().split("\n");
	  /*
	   *   "1.컵누들 스프와 후레이크를 넣고 뜨거운 물을 넣어요. 면이 잠길 정도면 적당해요.^https://recipe1.ezmember.co.kr/cache/recipe/2024/08/08/63b1c1893de0b820694a874018f6a9f91.jpg
2.팬에 기름을 두르고 대파를 볶아요. 프라이팬 , 조리용스푼^https://recipe1.ezmember.co.kr/cache/recipe/2024/08/08/98c771b5a1bd92d27134366a28b4957e1.jpg
3.파 향이 올라오면 달걀을 넣고 섞어요.^https://recipe1.ezmember.co.kr/cache/recipe/2024/08/08/e296a5122d5b9e37d3b36ae52532e98d1.jpg
4.달걀 흰자가 익기 시작하면 밥과 컵누들을 부어 강불에서 볶아요. 조리용가위 컵누들은 가위로 잘게 갈라 넣어요.^https://recipe1.ezmember.co.kr/cache/recipe/2024/08/08/e1219e68ac6b0b3a44c7f87df0ae9bc01.jpg
"
	   */
	  for(String fm:temp)
	  {
		  StringTokenizer st=new StringTokenizer(fm,"^");
		  sList.add(st.nextToken());
		  iList.add(st.nextToken());
	  }
	  /*
	   *   대파 1/3대 구매,컵누들 매콤한맛 1개 구매,현미밥 1/2공기 구매,달걀 1개 구매,식용유 2숟가락 구매,물 뜨거운 물 160ml 구매,프라이팬구매,조리용스푼구매,조리용가위구매,접시구매
	   */
	  String s=vo.getData();
	  s=s.replace("구매", "");
	  vo.setData(s.trim());
	  
	  request.setAttribute("vo", vo);
	  request.setAttribute("foodsList", sList);
	  request.setAttribute("imgList", iList);
	  
	  request.setAttribute("main_jsp", "../recipe/recipe_detail.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("recipe/chef_list.do")
  public String chef_list(HttpServletRequest request,HttpServletResponse response)
  {
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  int rowSize=50;
	  int start=(curpage*rowSize)-(rowSize-1);
	  int end=curpage*rowSize;
	  
	  Map map=new HashMap();
	  map.put("start", start);
	  map.put("end", end);
	  List<ChefVO> list=RecipeDAO.recipeChefListData(map);
	  int totalpage=RecipeDAO.recipeChefTotalPage();
	  
	  request.setAttribute("chefList", list);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("curpage", curpage);
	  
	  request.setAttribute("main_jsp", "../recipe/chef_list.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("recipe/chef_make.do")
  public String chef_make(HttpServletRequest request,HttpServletResponse response)
  {
	  String chef=request.getParameter("chef");
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  int rowSize=20;
	  int start=(curpage*rowSize)-(rowSize-1);
	  int end=curpage*rowSize;
	  Map map=new HashMap();
	  map.put("start", start);
	  map.put("end", end);
	  map.put("chef", chef);
	  // 데이터베이스 연동 
	  List<RecipeVO> list=RecipeDAO.recipeChefMakeData(map);
	  int totalpage=RecipeDAO.recipeChefMakeTotalPage(chef);
	  
	  request.setAttribute("chefList", list);
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("chef", chef);
	  
	  request.setAttribute("main_jsp", "../recipe/chef_make.jsp");
	  return "../main/main.jsp";
  }
  
}
