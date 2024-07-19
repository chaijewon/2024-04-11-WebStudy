<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<jsp:useBean id="model" class="com.sist.model.BoardModel"/>
<%
     // Model method 호출 => JSP마다 9개의 내장 객체를 가지고 있다
     model.boardInsertOk(request, response);
%>
