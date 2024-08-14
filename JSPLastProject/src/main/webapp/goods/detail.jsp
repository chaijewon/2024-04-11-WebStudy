<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.a{
	    white-space:nowrap;
	    overflow: hidden;
	    text-overflow: ellipsis;
	}
	#image{
		width: 100%;
		height: 350px;
		border-radius: 20px;
	}
	#title{
		font-size: 25px;
		font-weight: bold;
	}
	#sub{
		color:grey;
		text-align: center;
	}
	#percent{
		font-size: 25px; /*h3*/
		color: pink;
		font-weight: bold;
	}
	#price{
		font-size: 25px; /*h3*/
		font-weight: bold;
	}
	#psub{
		font-size: 12px;
		color: #999;
	}
	#star{
		color: orange;
		font-weight: bold;
	}
	#blod{
		font-weight: bold;
	}
	#count{
		color:gray
	}
	#sel{
		width: 100%;
		height: 40px;
	}
	#cart,#buy{
		width: 250px;
		height: 70px;
		border: 2px green solid;
		font-size: 20px;
		font-weight: bold;
		border-radius: 10px;
		box-shadow: 5px 5px 5px #A9A9A9;
	}
	#cart:hover , #buy:hover{
		cursor: pointer;
	}
	#cart{
		
		background-color: white;
		color:green;
	}
	#buy{
		background-color: green;
		color:white;
	}
</style>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
   <h2 class="sectiontitle">상세보기</h2>
   <table class="table">
	   <tr>
		   <td width="30%" align="center" rowspan="9">
			   <img src="${vo.goods_poster}"
			     id="image" 
			   >
		   </td>
		   <td width="60%" class="tdcenter">
			   <span id="title">
				  ${vo.goods_name}
			   </span>
		   </td>
	   </tr>
	   <tr>
		   <td width="60%">
			   <span id="sub">${vo.goods_sub}</span>
		   </td>
	   </tr>
	   <tr>
		   <td width="60%">
			   <span id="percent">${vo.goods_discount}%</span>&nbsp;
			   <span id="price" data-value="${vo.price}">${ vo.goods_price}</span>
			   <!--  데이터를 저장할때 속성은 사용자 정의로 가능하다 -->
			   <p>
				   <del id="psub">599,000원</del>
			   </p>
		   </td>
	   </tr>
	   <tr>
		   <td width="60%">
			   <span id="star">★★★★★</span>
			   <span id="blod">4.8</span>
			   <span id="count">(5)</span>
		   </td>
	   </tr>
	   <tr>
		   <td width="60%">
			   <img src="https://recipe1.ezmember.co.kr/img/mobile/icon_delivery3.png" width="20" height="20">
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   <span id="del">${vo.goods_delivery}</span>
		   </td>
	   </tr>
	   <tr>
		   <td width="60%">
			   <img src="https://recipe1.ezmember.co.kr/img/mobile/icon_point.png" width="20" height="20">
			   적립&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   <span id="del">1,595원</span> 적립 (모든 회원 구매액의 0.5% 적립)</span>
		   </td>
	   </tr>
	   <tr>
		   <td width="60%">
			   <select id="sel">
				   <option>옵션선택</option>
			   </select>
		   </td>
	   </tr>
	   <tr>
		   <td width="60%" class="inline">
			   <input type="button" value="장바구니" id="cart">
			   <input type="button" value="바로구매" id="buy"
			    onclick="requestPay()"
			   >
		   </td>
	   </tr>
   </table>
  </main>
</div>
</body>
</html>