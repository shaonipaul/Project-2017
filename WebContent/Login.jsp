<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta charset="UTF-8">
  <title>LOGIN/REGISTER</title>
  
  
  <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:600'>

  <link rel="stylesheet" href="/Master/login.css" type="text/css">

  
</head>

<body>
  <div class="login-wrap">
	<div class="login-html">
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
		<div class="login-form">
			<div class="sign-in-htm">
			<form action="Register?form=login" method="post">
				<div class="group">
				
					<label for="user" class="label">Username</label>
					<input id="user" type="text" class="input" name="username">
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<input id="pass" type="password" class="input" data-type="password" name="password">
				</div>
				
				<div class="group">
					<input type="submit" class="button" value="Sign In">
				</div>
				<div><label for="pass" class="label">${error}</label></div>
				<div class="hr"></div>
				</form>
			</div>
			<div class="sign-up-htm">
			<form action="Register?form=register" method="post">
				<div class="group">
					<label for="user" class="label">Username</label>
					<input id="user" type="text" class="input" name="username">
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<input id="pass" type="password" class="input" data-type="password" name="password">
				</div>
				<div class="group">
					<label for="pass" class="label">Repeat Password</label>
					<input id="pass" type="password" class="input" data-type="password">
				</div>
				<div class="group">
					<label for="pass" class="label">Email Address</label>
					<input id="pass" type="text" class="input" name = "email">
				</div>
				<div class="group">
					<label for="pass" class="label">User</label>
					<input type="radio" name="user" value="faculty"> Faculty
  					<input type="radio" name="user" value="student"> Student
				</div>
				<div class="group">
					<input type="submit" class="button" value="Sign Up">
				</div>
				<div><label for="pass" class="label">${msg}</label></div>
				<div class="hr"></div>
				<div class="foot-lnk">
					<label for="tab-1">Already Member?</a>
				</div>
			</form>
			</div>
			
		</div>
	</div>
</div>
  
  
</body>
</html>
