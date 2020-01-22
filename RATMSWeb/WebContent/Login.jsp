<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>RATMS Login</title>

<link rel="stylesheet" type="text/css" href="CSS/util.css">
<link rel="stylesheet" type="text/css" href="CSS/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="JS/index.js"></script>


</head>
<body>
	<div class="container">
		<div class="container-login">
			<div class="wrap-login">
				<div class="login-title"
					style="background-image: url(Images/Background.jpg);">
					<span class="login-form-title"> ConnecTeam Login </span>
				</div>

				<form class="login100-form">

					<div id="error"></div>

					<div class="wrap-input m-b-26">
						<span class="label-input">Username</span> <input id="username"
							class="input" type="text" name="username"
							placeholder="Enter username"> <span class="focus-input"></span>
					</div>

					<div class="wrap-input m-b-18">
						<span class="label-input">Password</span> <input id="password"
							class="input" type="password" name="password"
							placeholder="Enter password"> <span class="focus-input"></span>
					</div>

					<div class="p-b-30">
						<div class="form-checkbox">
							<input id="rememberMe" class="input-checkbox" type="checkbox" name="rememberMe" value="rememberMe">
							<label class="label-checkbox" for="ckb">Remember me</label>
						</div>

					</div>


					<div class="container-login-form-btn">
						<input type="button" id="getin" class="login-form-btn"
							value="LOGIN">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>