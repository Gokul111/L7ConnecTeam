<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>RATMS_Login</title>

<link rel="stylesheet" type="text/css" href="CSS/util.css">
<link rel="stylesheet" type="text/css" href="CSS/main.css">
</head>
<body>
		<div class="container">
			<div class="container-login">
				<div class="wrap-login">
					<div class="login-title"
						style="background-image: url(Images/Background.jpg);">
						<span class="login-form-title"><fmt:message
								key="loginTitle"></fmt:message></span>
					</div>

					<form class="login100-form" action="loginServlet" method="POST">

						<div class="wrap-input m-b-26">
							<span class="label-input"><fmt:message key="userLabel"></fmt:message></span>
							<input class="input" type="text" name="username"
								placeholder="Enter username"> <span class="focus-input"></span>
						</div>

						<div class="wrap-input m-b-18">
							<span class="label-input"><fmt:message key="passwordLabel"></fmt:message></span>
							<input class="input" type="password" name="password"
								placeholder="Enter password"> <span class="focus-input"></span>
						</div>

						<div class="p-b-30">
							<div class="form-checkbox">
								<input class="input-checkbox" type="checkbox" name="rememberMe">
								<label class="label-checkbox" for="ckb"><fmt:message
										key="rememberLabel"></fmt:message></label>
							</div>

						</div>

						<div class="container-login-form-btn">
							<button class="login-form-btn">
								<fmt:message key="loginButton"></fmt:message>
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	
</body>
</html>