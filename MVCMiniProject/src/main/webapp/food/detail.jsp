<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
  <h2 class="sectiontitle">맛집 상세보기</h2> 
  <table class="table">
   <tr>
     <td width="30%" class="text-center" rowspan="5">
      <img src="${vo.poster }" style="width: 100%">
     </td>
     <td colspan="2">
      <h3>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h3>
     </td>
   </tr>
   <tr>
     <th width="20%" class="text-center">주소</th>
     <td width="50%">${vo.address }</td>
   </tr>
   <tr>
     <th width="20%" class="text-center">전화</th>
     <td width="50%">${vo.phone }</td>
   </tr>
   <tr>
     <th width="20%" class="text-center">음식종류</th>
     <td width="50%">${vo.type }</td>
   </tr>
   <tr>
     <th width="20%" class="text-center">테마</th>
     <td width="50%">${vo.theme }</td>
   </tr>
  </table>
  <table class="table">
   <tr>
    <td>${vo.content }</td>
   </tr>
  </table>
  <div style="height: 10px"></div>
  <h2 class="sectiontitle">인근 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="svo" items="${sList }">
       <li>
          <figure><img class="radius-10 btmspace-10" src="${svo.poster }" style="width: 320px;height: 185px">
            <figcaption><a href="#">${svo.name }</a></figcaption>
          </figure>
        </li>
       </c:forEach>
      </ul>
    </div>
  </main>
</div>
</body>
</html>