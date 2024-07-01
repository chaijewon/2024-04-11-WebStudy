<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String key=request.getParameter("key");
    
%>
 <embed src="<%=key %>" id="movie" width=470 height=420></embed>