<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    SeoulDAO dao=SeoulDAO.newInstance();
    //쿠키 출력 
	Cookie[] cookies=request.getCookies();
	List<LocationVO> cList=new ArrayList<LocationVO>();
	if(cookies!=null)
	{
		// 가장 최신에 등록된 쿠키 데이터 읽기
		for(int i=cookies.length-1;i>=0;i--)
		{
			if(cookies[i].getName().startsWith("seoul_"))
			{
				String no=cookies[i].getValue();
				LocationVO vo=dao.seoulDetailData(Integer.parseInt(no));
				cList.add(vo);
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 960px;
}
.a{
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
</style>
</head>
<body>
  <div class="container">
    <%
        if(cList.size()<1)
        {
    %>
           <div class="row">
            <h3 class="text-center">
             최근 방문기록이 없습니다
            </h3>
           </div>
    <%
        }
        else
        {
    %>
    
    <div class="row">
       <h3 class="text-center">최근 방문 서울 여행지</h3>
      <%
         for(LocationVO vo:cList)
         {
        	 /*
        	    response
        	      응답 : HTML / Cookie 
        	      화면 변경 : sendRedirect()
        	    request 
        	      요청 : 데이터를 전송 
        	            쿠키 읽기 / 세션 읽기
        	            사용자 보내주는 모든 데이터는 request에 저장 
        	            ?값 , <form> 
        	     Spring : 사용이 쉽게 만들어준 라이브러리 
        	              ============== 형식 1개 (표준화) 
        	              => 출력시에는 JSP를 이용한다 / 처리 자바를 이용한다 
        	              
        	              
        	     구구단 (2차 for) / 페이지 (1차 for) 
        	     ============= << 
        	 */
      %>
            <div class="col-sm-3">
             <div class="thumbnail">
              <a href="detail_before.jsp?no=<%=vo.getNo()%>">
               <img src="<%=vo.getPoster() %>" style="width: 240px;height: 200px" onerror="this.src='no_img.png'">
               <p class="a"><%=vo.getTitle() %></p>
              </a>
             </div>
            </div>
      <%
         }
        }
      %>
    </div>
    </div>
</body>
</html>