package com.sist.model;
/*
 *   MVC 
 *           HTTP요청(URL)      웹 컨테이너 (톰캣)
 *   웹브라우저 =============   ============================================
 *     |                       Controller
 *                             -----------
 *                             1. 요청을 받는다 getRequestURI() 
 *     |                       2. URI를 이용해서 요청 내용확인 => list.do,insert.do
 *                             3. 요청에 대한 처리 => Model이 가지고 있는 메소드 호출 
 *                             4. Model에서 넘겨주는 request/session 가지고 온다 
 *                                               ======= request.setAttribute()
 *                             5. JSP로 값을 전송 (forward=request전송, sendRedirect()이용 화면 이동
 *                                               Model        DAO           오라클
 *     |                       -----------      -------      -------        -----
 *     |                                        요청처리         오라클연동        사이트에 필요한 
 *                                                            필요한 데이터를     공유하는 데이터를 모아서 관리
 *                                                            가지고 온다 
 *                                              request/session
 *                                              에 값을 담아 준다 
 *     |                                        -------      -------        -----
 *     |
 *     |                     ============================================
 *     |                      JSP
 *     |                     --------
 *     |--------------------                     
 *                           --------
 *                           
 *     1. 브라우저 => URL을 이용해서 서버연동 (주소창)
 *     2. URL을 이용해서 서버연결이 되면 => Controller (URL를 읽을 수 있는 프로그램 : JSP / Servlet)
 *        => 요청을 받아서 => 처리 => JSP로 결과값 전송 
 *           ================================= 화면출력이 없이 연결 (서블릿) 
 *           JSP : Front-End (결과값을 받아서 출력)
 *     3. MVC의 단점이 Controller 집중적이다 
 *        ============================= MVVM , MVP ...
 *        
 *     4. Controller : 역할 (고정) => 소스 수정이 거의 없다 
 *                     => 필요한 데이터는 파일을 만들어서 전송 
 *                        ==========================
 *                         | XML , properties
 *                     => 요청 받기 
 *                     => Model클래스 / Model안에 메소드 호출
 *                                    ================= 구분자 (Annotation)
 *                                                            => 최종 : Spring
 *                      
 */
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.*;
import com.sist.dao.*;
import com.sist.vo.*;

import java.io.PrintWriter;
import java.text.*;
public class BoardModel {
	/*
	 *   Spring 
	 *   @Autowired => 자동 메모리 주소 주입 => 싱글턴 (new 사용하지 않는다)
	 *   private BoardDAO dao;
	 */
	private BoardDAO dao=BoardDAO.newInstance();
	
    @RequestMapping("board/list.do")
    public String board_list(HttpServletRequest request,HttpServletResponse response)
    {
    	// page => 받기 
    	String page=request.getParameter("page");
    	if(page==null)
    		page="1";
    	// 정수형 변환 => 처리 가능 
    	int curpage=Integer.parseInt(page); // 오류 
    	List<BoardVO> list=dao.boardListData(curpage);
    	/*
    	 *   URL 
    	 *   ====
    	 *     list.do =============> null if(page==null)
    	 *     list.do?page= =======> ""   if(page.equals(""))
    	 *     list.do?page=1 ======> "1"
    	 *     list.do? page = 1 ===> 오류발생
    	 *       => string input " 1" 
    	 */
    	int count=dao.boardRowCount();
    	// 총 게시물 갯수 
    	int totalpage=(int)(Math.ceil(count/10.0));
    	// 총페이지 
    	count=count-((curpage*10)-10);
    	//     20 - 0   = 20
    	//     20 -((2*10)-10) => 10
    	// list.jsp로 출력 데이터 전송 
    	request.setAttribute("list", list);
    	request.setAttribute("curpage", curpage);
    	request.setAttribute("totalpage", totalpage);
    	request.setAttribute("count", count);
    	Date date=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	String today=sdf.format(date);
    	// new SimpleDateFormat("yyyy-MM-dd").format(new Date())
    	request.setAttribute("today", today );
    	request.setAttribute("main_jsp", "../board/list.jsp");
    	// main.jsp에 include 가 되는 파일 지정 
    	return "../main/main.jsp";
    }
    /*
     *   JSP <a> ==> Controller => Model => JSP
     *   MVVM => VueJS 
     *           ====== ReactJS(Dev) / Docker,MSA(Ops)
     */
    @RequestMapping("board/insert.do")
    public String board_insert(HttpServletRequest request,HttpServletResponse response){
    	request.setAttribute("main_jsp", "../board/insert.jsp");
    	return "../main/main.jsp";
    }
    @RequestMapping("board/insert_ok.do")
    public String board_insert_ok(HttpServletRequest request,HttpServletResponse response)
    {
    	try
    	{
    		request.setCharacterEncoding("UTF-8");
    	}catch(Exception ex) {}
    	BoardVO vo=new BoardVO();
    	vo.setName(request.getParameter("name"));
    	vo.setSubject(request.getParameter("subject"));
    	vo.setContent(request.getParameter("content"));
    	vo.setPwd(request.getParameter("pwd"));
    	// DataBase연동 => DAO
    	dao.boardInsert(vo);
    	return "redirect:../board/list.do";//sendRedirect
    }
    // @RequestMapping => 중복이 되면 오류발생 
    // => @GetMapping() , @PostMapping() => 412
    // => @RequestMapping() => GET/POST 통합 
    @RequestMapping("board/detail.do")
    public String board_detail(HttpServletRequest request,HttpServletResponse response)
    {
    	String no=request.getParameter("no");
    	String row=request.getParameter("row");
    	
    	// DAO에서 상세보기에 출력할 데이터 읽기
    	BoardVO vo=dao.boardDetailData(Integer.parseInt(no));
    	request.setAttribute("vo", vo);
    	request.setAttribute("main_jsp", "../board/detail.jsp");
    	return "../main/main.jsp";
    }
    @RequestMapping("board/update.do")
    public String board_update(HttpServletRequest request,HttpServletResponse response)
    {
    	// board/update.do?no=${vo.no }
    	String no=request.getParameter("no");
    	BoardVO vo=dao.boardUpdateData(Integer.parseInt(no));
    	request.setAttribute("vo", vo);
    	request.setAttribute("main_jsp", "../board/update.jsp");
    	return "../main/main.jsp";
    }
    @RequestMapping("board/update_ok.do")
    public void board_update_ok(HttpServletRequest request,HttpServletResponse response)
    {
    	try
    	{
    		request.setCharacterEncoding("UTF-8");
    	}catch(Exception ex) {}
    	BoardVO vo=new BoardVO();
    	vo.setName(request.getParameter("name"));
    	vo.setSubject(request.getParameter("subject"));
    	vo.setContent(request.getParameter("content"));
    	vo.setPwd(request.getParameter("pwd"));
    	String no=request.getParameter("no");
    	vo.setNo(Integer.parseInt(no));
    	
    	boolean bCheck=dao.boardUpdate(vo);
    	
    	try
    	{
    	    // Ajax 처리 
    		response.setContentType("text/html;charset=UTF-8");
    		PrintWriter out=response.getWriter();
    		
	    	if(bCheck==true)// 비밀번호가 맞는 경우
	    	{
	    		out.write("<script>");
	    		out.write("location.href=\"../board/detail.do?no="+no+"\"");
	    		out.write("</script>");
	    	}
	    	else
	    	{
	    		out.write("<script>");
	    		out.write("alert(\"비밀번호가 틀립니다!!\");");
	    		out.write("history.back();");
	    		out.write("</script>");
	    	}
    	}catch(Exception ex){}
    }
}





