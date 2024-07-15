<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
    String no=request.getParameter("no");
    DataBoardDAO dao=DataBoardDAO.newInstance();
    DataBoardVO vo=dao.databoardDetailData(Integer.parseInt(no));
    //          == vo있는 데이터 8개 HTML을 이용해서 출력 
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
  width: 800px;
}
h3{
   text-align: center;
}
</style>
</head>
<body>
  <div class="container">
    <h3>내용보기</h3>
    <div class="row">
      <table class="table">
        <tbody>
          <tr>
           <th width=20% class="text-center danger">번호</th>
           <td width=30% class="text-center"><%=vo.getNo() %></td>
           <th width=20% class="text-center danger">작성일</th>
           <td width=30% class="text-center"><%=vo.getDbday() %></td>
          </tr>
          <tr>
           <th width=20% class="text-center danger">이름</th>
           <td width=30% class="text-center"><%=vo.getName() %></td>
           <th width=20% class="text-center danger">조회수</th>
           <td width=30% class="text-center"><%=vo.getHit() %></td>
          </tr>
          <tr>
           <th width=20% class="text-center danger">제목</th>
           <td colspan="3"><%=vo.getSubject() %></td>
          </tr>
          <%
             if(vo.getFilesize()!=0)
             {
          %>
		          <tr>
		           <th width=20% class="text-center danger">첨부파일</th>
		           <td colspan="3">
		            <a href="download.jsp?fn=<%=vo.getFilename()%>"><%= vo.getFilename() %></a>(<%=vo.getFilesize() %>Bytes)
		           </td>
		          </tr>
          <%
             }
          %>
          <tr>
           <td colspan="4" class="text-left" valign="top" height="200">
            <pre style="white-space: pre-wrap;border: none;background-color: white"><%=vo.getContent() %></pre>
           </td>
          </tr>
          <tr>
            <td colspan="4" class="text-right">
             <a href="#" class="btn btn-xs btn-success">수정</a>
             <a href="#" class="btn btn-xs btn-info">삭제</a>
             <a href="list.jsp" class="btn btn-xs btn-warning">목록</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>