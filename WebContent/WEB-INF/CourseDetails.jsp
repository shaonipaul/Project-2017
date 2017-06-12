<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="/Master/home.css" type="text/css">
<link rel="stylesheet" href="/Master/chat.css" type="text/css">
 <script src="/Master/chat.js"></script>
<script>
$(document).ready(function(){
	$("#box").hide();
	$("#box1").hide();
    
	$("#close").click(function(){
        
        $('#main').fadeTo("slow",1);
        $("#box").hide();
    });
	
	$("#close1").click(function(){
        
        $('#main').fadeTo("slow",1);
        $("#box1").hide();
    });
    
});

function close(){
	 $('#main').fadeTo("slow",1);
     $("#box").hide();
}

  function setVal(val){
	  var i = document.getElementById("q");
	   i.value = val; 
	  $('#main').fadeTo("slow",0.05);
      $("#box").show();
  }
  function setVal1(val){
	  var i = document.getElementById("q1");
	   i.value = val; 
	  $('#main').fadeTo("slow",0.05);
      $("#box1").show();
  }
</script>
</head>

<body>
<%HttpSession se = request.getSession();
Map<String,String> m = (Map<String,String>)se.getAttribute("mapvalue");
%>

<input type="hidden" value = "<%=m.get("id") %>" id="uid"/>
<input type="hidden" value = "<%=m.get("username") %>" id="uname"/>
<% Map<String, Object> map = (Map<String, Object>)request.getAttribute("map"); %>
<h1><%=map.get("cname") %></h1>


<div id="main">	
<main>
  
  <input id="tab1" type="radio" name="tabs" checked>
  <label for="tab1">Syllabus</label>
    
  <input id="tab2" type="radio" name="tabs">
  <label for="tab2">Lecture</label>
    
  <input id="tab3" type="radio" name="tabs">
  <label for="tab3">Exam</label>
    
  <input id="tab4" type="radio" name="tabs">
  <label for="tab4">Other info</label>
 
	
  <section id="content1">
  
  	<div>
	<input class="buttonmain" type="button" onClick="setVal('syllabus')"
	style="width:100%;
	display:inline;
	color:white;
	display:block;
	border:none;
	padding:15px 20px;
	border-radius:25px;
	background:#7F9DFE;"  value="Add or edit syllabus">
	</div> 
	
    <table>
    <tr>
    <% try{
    List<Map<String, String>> l1 = (List<Map<String, String>>) map.get("syllabus");
    Map<String, String> m1  = l1.get(0); %>
    <td>
    <% out.print(m1.get("content"));%>
    </td>
    <td>
    <a href="DownloadFile?type=syllabus&fid=<%out.print(m1.get("sid"));%> "><% out.print(m1.get("name")); %></a>
    </td>
    
    <%}catch(Exception e){}
    	%>
    </tr>
    </table>
  </section>
    
  <section id="content2">
  <div>
	<input class="buttonmain" type="button" onClick="setVal('lecture')"
	style="width:100%;
	display:inline;
	color:white;
	display:block;
	border:none;
	padding:15px 20px;
	border-radius:25px;
	background:#7F9DFE;" value="Add contents for lecture">
	</div> 
   <table>
   
     <% try{
    	 List<Map<String, String>> l2 = (List<Map<String, String>>) map.get("lecture");
    	 for(Map<String, String> m2:l2){ %>
   <tr>
   <td>
   <%  out.print(m2.get("content"));%>
    </td>
    <td>
    <a href="DownloadFile?type=lecture&fid=<%out.print(m2.get("lid"));%> "><% out.print(m2.get("name")); %></a>
    </td>
    <td>
   <a href="DeleteFile?type=lecture&fid=<%out.print(m2.get("lid"));%>&cid=<%out.print(map.get("cid") );%>">Delete</a>
   </td>
    </tr>
    
    <%}}catch(Exception e){} %>
    </table>
  </section>
    
  <section id="content3">
  <div>
	<input class="buttonmain" type="button" onClick="setVal1('exam')"
	style="width:100%;
	display:inline;
	color:white;
	display:block;
	border:none;
	padding:15px 20px;
	border-radius:25px;
	background:#7F9DFE;" value="Add exam">
	</div> 
    <p>
     <%=map.get("cexam") %>
    </p>
  </section>
    
  <section id="content4">
  <div>
	<input class="buttonmain" type="button" onClick="setVal1('info')"
	style="width:100%;
	display:inline;
	color:white;
	display:block;
	border:none;
	padding:15px 20px;
	border-radius:25px;
	background:#7F9DFE;" value="Add or edit details about location and TA information">
	</div> 
    <p>
     <%=map.get("cinfo") %>
    </p>
  </section>
  </main>
  </div>
  
  
  <div id="box" style="background:#EBF5FA;position:fixed;top:30%;left:35%; padding: 20px;border-radius: 5px; width: 30%;">
  
  <form action="Course" method="post" enctype="multipart/form-data">
  <input type="hidden" id="q" value="" name="type">
  <input type="hidden" value="<%out.print(map.get("cid") );%>" name="cid">
 
  <textarea rows="5" cols="50" name="content"></textarea>
  <input type="file" id="boxbutton" name="file" accept="image/*,xls,.xlsx,doc,.docx,.pdf, audio/*, video/*" style="width:100%;
	display:inline;
	color:#7F9DFE;
	display:block;
	border:none;
	padding:15px 20px;
	border-radius:25px;
	background:transparent;">
	<input type="submit" id="boxbutton"  value ="Submit" style="width:100%;
	display:inline;
	color:white;
	display:block;
	border:none;
	padding:10px 10px;
	border-radius:25px;
	background:#7F9DFE;">
	</form>
	<input class="buttonmain" type="button" id="close"
	style="width:100%;
	display:inline;
	color:white;
	display:block;
	border:none;
	padding:10px 10px;
	border-radius:25px;
	background:#7F9DFE;" value="Cancel">
	
	
	</div>
	<div id="box1" style="background:#EBF5FA;position:fixed;top:30%;left:35%; padding: 20px;border-radius: 5px; width: 30%;">
  
  <form action="Course" method="post" enctype="multipart/form-data">
  <input type="hidden" id="q1" value="" name="type">
  <input type="hidden" value="<%out.print(map.get("cid") );%>" name="cid">
 
  <textarea rows="5" cols="50" name="content"></textarea>
  
	<input type="submit" id="boxbutton1"  value ="Submit" style="width:100%;
	display:inline;
	color:white;
	display:block;
	border:none;
	padding:10px 10px;
	border-radius:25px;
	background:#7F9DFE;">
	</form>
	<input class="buttonmain" type="button" id="close1"
	style="width:100%;
	display:inline;
	color:white;
	display:block;
	border:none;
	padding:10px 10px;
	border-radius:25px;
	background:#7F9DFE;" value="Cancel">
	
	
	</div>
	<div class="chat-sidebar" id="side">
        <h5>Members online</h5> 
        <input class="buttonmain" type="button" id="logout" value="Log out">
        </div>
 </body>     
</html>
