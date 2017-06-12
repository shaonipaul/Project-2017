<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<h1>Courses</h1>
 
<main>

<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="/Master/home.css" type="text/css">
<link rel="stylesheet" href="/Master/chat.css" type="text/css">
 <script src="/Master/chat.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Courses</title>
</head>

<body>

<%HttpSession se = request.getSession();
Map<String,String> m = (Map<String,String>)se.getAttribute("mapvalue");
%>

 <div style="float: left;display: block">
 <a href="#news"><%=m.get("username") %> is logged in ||</a>
 <a href="Login.jsp">Logout</a>
 </div> 
 
<input type="hidden" value = "<%=m.get("id") %>" id="uid"/>
<input type="hidden" value = "<%=m.get("username") %>" id="uname"/>
<table style="width: 100%;text-align: left">
  <tr>
    <th>Course ID</th>
    <th>Course Name</th>
    <th>Semester</th>
  </tr>
  
  <c:forEach var="map" items="${list}">
  
  <tr>
    <td><a href="Course?cid=${map['cid'] }">${map['ccid']}</a></td>
    <td>${map['cname']}</td>
    <td>${map['csemester']}</td>
  </tr>
  
  </c:forEach>
  
</table>

<div class="chat-sidebar" id="side">
        <h5>Members online</h5> 
       
</div>



</body>
</main>
</html>