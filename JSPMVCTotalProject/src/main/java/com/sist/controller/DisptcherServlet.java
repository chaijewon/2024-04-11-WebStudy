package com.sist.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.model.Model;
/*
 *   1. JSP / Servlet => Model 1방식
 *      = 장점 
 *         1) 서블릿 => 자바코드만으로 HTML 코드 작성 => 오류 발생할 확률이 낮다
 *         2) JSP => 자바코드만으로 HTML 코드 작성 => 오류 발생할 확률이 낮다
 *         3) 단순하다 => 자바/HTML을 구분 
 *                     <% %> , <%= %> => 제어문 : for / if 
 *      = 단점 
 *         1) 가독성이 떨어지다 => Java + HTML 
 *         2) 자바의 로직이나 데이터베이스 => 노출이 된다 (JSP는 배포시에 모든 소스를 전달)
 *                                  =========
 *                                  다른 개발자 => 유지보수 
 *                                  실제 개발 : 6개월 개발 / 6개월 유지보수 (SI/SM)
 *                                           솔루션 / SM
 *         3) 파일 1개 작업을 하기 때문에 분업이 어렵다 
 *            => 만능 (HTML/CSS/JavaScript/Java/Oracle)
 *      = 사용처 
 *        => 개인 기업 사이트 
 *        => 유지보수가 거의 없는 사이트
 *           => JSP/Servlet : 확장성 / 재사용성 => 많이 떨어진다  
 *        => 개인 홈페이지 (유치원 , 개인 쇼핑몰, 개인 학원) 
 *        => 간단한 사이트 제작 
 *   2. MVC => Model 2방식 
 *      => JSP/Servlet => 보완 
 *         => 1. 확장성 / 재사용 증가 
 *         => 2. 보안이 필요한 경우 처리 (HTML/Java 완전 분리) 
 *         => 3. 분업화 => Front-End / Back-End 
 *         => 4. Controller로 집중적 => 서버에 부하가 많을 수 있다 
 *               ======================================
 *               1) Front-End의 MVC => Redux / Flux / Vuex 
 *               2) 서버를 나눠서 동시 구동 => MSA 
 *                                     ==== 애자일 기법 
 *         => 비용이 많이 들어간다 
 *         => 목적은 유지 보수 목적 => 쉽게 수정 / 쉽게 추가 
 *         => 모든 편집기 => 어시스트 
 *       => 공기업 / 금융권 / 대기업 : MVC 구조로 만들어져 있다 
 *          ==================  =======
 *                              | => 스프링 
 *          스프링 / 웹스페어 / 웹로직
 *                          | 1대당 100만원 
 *           | 무료버젼 
 *          
 *          라이브러리 VS 프레임워크 => 라이브러리 
 *           |          |
 *          완제품       레고 (자바소스를 제공) => 전자정부 프레임워크 , 애니 프레임워크 
 *          
 *          스프링 => 6버전 19 
 *          ====
 *          실무 => 4버전 
 *                5버전  17 
 *          스프링 => 버전이 너무 자주 변경된다 => 버전호환  
 *          ==================================
 *          노트북 제공 
 *          
 *          기능 처리 
 *          1. 클래스화 => 관리가 어렵다 
 *                      게시판 => 기능 => 12개 
 *          2. 메소드화 => 1개의 클래스에 메소드 12개 
 *             ======> 구분자 (어노테이션) => 스프링 
 *          
 */
// Controller는 고정 => 추가 , 수정 (파일 => XML)
@WebServlet("*.do")
public class DisptcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String[] strCls={
    	"com.sist.model.MovieListModel",
    	"com.sist.model.MovieDetailModel"
    };
    private String[] strCmd= {
    		"list.do",
    		"detail.do"
    };
    private Map clsMap=new HashMap();
    // => 1. 클래스 등록 , 2. SQL문장 등록 , 3. 클라이언트 정보등록 
    //       Spring       MyBatis         WebSocket
    // => 웹은 거의 대부분이 Map방식을 사용 
    // request,session,cookie => 키,값 
    // => JSON => 자바스크립트 객체 표현법 
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try
		{
			for(int i=0;i<strCls.length;i++)
			{
				Class clsName=Class.forName(strCls[i]); // 클래스 정보 읽기 
				// => 제어 : 리플렉션 
				Object obj=clsName.getDeclaredConstructor().newInstance();
				// 클래스 메모리 할당 
				// new => 결합성이 높다 (new사용을 자제) => 스프링에서는 new를 사용하지 않는다 
				clsMap.put(strCmd[i], obj);
				System.out.println(strCmd[i]+":"+obj);
				// 시작과 동시에 => 클래스 메모리 저장 => 변경이 없다 (싱글턴)
			}
		}catch(Exception ex){}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*Model model1=(Model)clsMap.get("list.do");
		Model model2=(Model)clsMap.get("list.do");
		Model model3=(Model)clsMap.get("list.do");
		Model model4=(Model)clsMap.get("list.do");
		Model model5=(Model)clsMap.get("list.do");
		System.out.println("model1="+model1);
		System.out.println("model2="+model2);
		System.out.println("model3="+model3);
		System.out.println("model4="+model4);
		System.out.println("model5="+model5);*/
		// 사용자 요청 사항 
		// http://localhost/JSPMVCTotalProject/movie/list.do
		String cmd=request.getRequestURI();
		// 해당 Model찾기 => 기능별로 나눠서 작업 
		cmd=cmd.substring(cmd.lastIndexOf("/")+1);
	    Model model=(Model)clsMap.get(cmd);
		// JSP에 전송할 데이터를 가지고 온다 
	    String jsp=model.execute(request, response);
		// JSP => request전송 
	    RequestDispatcher rd=request.getRequestDispatcher(jsp);
	    rd.forward(request, response);
		
	}

}
