<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    width: 960px
  }
  .a{
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
  }
  .images:hover{
    cursor: pointer;
  }
 </style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="food.js"></script>
</head>
<body>
  <div class="container">
   <div class="row">
     <div class="text-center">
      <input type="button" class="btn btn-lg btn-danger typeBtn"
        value="한식" onclick="change(1)">
      <input type="button" class="btn btn-lg btn-success typeBtn"
        value="중식" onclick="change(2)">
      <input type="button" class="btn btn-lg btn-primary typeBtn"
        value="양식" onclick="change(3)">
      <input type="button" class="btn btn-lg btn-info typeBtn"
        value="일식" onclick="change(4)">
      <input type="button" class="btn btn-lg btn-warning typeBtn"
        value="분식" onclick="change(5)">
     </div>
   </div>
   <div style="height: 10px"></div>
   <div class="row" id="print">
    
   </div>
   <div style="height: 10px"></div>
   <div class="row" id="print">
     <div class="text-center">
      <input type=button class="btn btn-sm btn-success" value="이전" id="prev" data-type="">
      <span id="cpage"></span> page / <span id="tpage"></span> pages
      <input type=button class="btn btn-sm btn-success" value="다음" id="next" data-type="">
     </div>
   </div>
  </div>
</body>
</html>