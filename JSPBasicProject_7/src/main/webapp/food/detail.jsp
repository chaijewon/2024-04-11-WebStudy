<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<%
    String id=(String)session.getAttribute("id");
    String fno=request.getParameter("fno");
    // include => 파일전체에서 request를 공유할 수 있다 
    // mode => 화면 변경 => include되는 파일 찾기 
    // 나머지 데이터는 해당 JSP에서 처리 
    FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
    // 댓글목록 => fno
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="row">
    <div class="col-sm-8">
      <%-- 맛집 상세 , 댓글 --%>
      <table class="table">
       <tr>
         <td width="30%" class="text-center" rowspan="5">
          <img src="<%=vo.getPoster() %>" style="width: 100%">
         </td>
         <td colspan="2"><h3><%=vo.getName() %>&nbsp;<span style="color:orange"><%=vo.getScore() %></span></h3></td>
       </tr>
       <tr>
         <th class="text-center" width="15%">음식종류</th>
         <td width="55%"><%=vo.getType() %></td>
       </tr>
       <tr>
         <th class="text-center" width="15%">주소</th>
         <td width="55%"><%=vo.getAddress() %></td>
       </tr>
       <tr>
         <th class="text-center" width="15%">전화번호</th>
         <td width="55%"><%=vo.getPhone() %></td>
       </tr>
       <tr>
         <th class="text-center" width="15%">테마</th>
         <td width="55%"><%=vo.getTheme() %></td>
       </tr>
      </table>
      <table class="table">
        <tr>
          <td><%=vo.getContent() %></td>
        </tr>
        <tr>
          <td class="text-right">
            <a href="#" class="btn btn-xs btn-info">찜하기</a>
            <a href="#" class="btn btn-xs btn-success">좋아요</a>
            <a href="#" class="btn btn-xs btn-warning">예약</a>
            <a href="javascript:history.back()" class="btn btn-xs btn-primary">목록</a>
          </td>
        </tr>
      </table>
      <div style="height: 15px"></div>
      
      <%
         if(id!=null)
         {
      %>
      <table class="table">
        <tr>
          <td>
           <form method="post" action="../reply/insert.jsp">
            <input type="hidden" name=fno value="<%=vo.getFno()%>">
            <textarea rows="4" cols="60" name="msg" style="float: left" required></textarea>
            <button style="height: 82px;width: 120px;background-color: blue;color:white;float: left">댓글쓰기</button>
           </form>
          </td>
        </tr>
      </table>
      <%
         }
      %>
    </div>
    <div class="col-sm-4">
      <%-- 지도 , 근처 맛집 --%>
    </div>
  </div>
</body>
</html>