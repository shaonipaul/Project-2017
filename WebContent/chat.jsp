<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat</title>
<link rel="stylesheet" href="/Project2017/chat.css" type="text/css">
 <script src="/Project2017/chat.js"></script> 
</head>
<body>

<form>
		<input id="message" type="text">
		<input onclick="wsOpenConnection();" value="Open" type="button">
		<input onclick="wsSendMessage();" value="Echo" type="button">
		<input onclick="wsCloseConnection();" value="Disconnect" type="button">
	</form>
	<br>
	<textarea id="echoText" rows="5" cols="30"></textarea>
	
	
        <div class="chat-sidebar">
            <div class="sidebar-name">
                <!-- Pass username and display name to register popup -->
                <a href="javascript:register_popup('1', 'Narayan Prusty');">
                    <span>Narayan Prusty</span>
                </a>
            </div>
            <div class="sidebar-name">
                <a href="javascript:register_popup('2', 'QNimate');">
                    <span>QNimate</span>
                </a>
            </div>
            <div class="sidebar-name">
                <a href="javascript:register_popup('3', 'QScutter');">
                    <span>QScutter</span>
                </a>
            </div>
            <div class="sidebar-name">
                <a href="javascript:register_popup('4', 'QIdea');">
                    <span>QIdea</span>
                </a>
            </div>
            <div class="sidebar-name">
                <a href="javascript:register_popup('5', 'QAzy');">
                    <span>QAzy</span>
                </a>
            </div>
            <div class="sidebar-name">
                <a href="javascript:register_popup('6', 'QBlock');">
                    <span>QBlock</span>
                </a>
            </div>
        </div>
        
    </body>
</html>