<%@page import="dto.CustUserBean"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> This is Model1 Practice. </h1>
	
	<!-- <a href = "custusercontrol.jsp?command=list">All Custuser</a> -->
	<%
	// 진입 즉시 페이지 전환. 
	response.sendRedirect("custusercontrol.jsp?command=list");
	%>
	
</body>
</html>