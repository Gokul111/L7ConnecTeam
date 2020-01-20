<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script src="JS/home.js">
	
</script>
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="CSS/styles.css">
<script src="JS/home.js">
	
</script>
<title>L7 ConnecTeam</title>
</head>
<body>
	<form action="loginServlet" method="get">
		<div id="header">
			<img src="Images/Litmus7_logo.jpg" id="logo" alt="Litmus-Logo"
				height="50" width="80"> <input type="submit" value="Logout"
				class="logout-btn" onclick="clearCookies()">
		</div>
		<div class="session-val">
			<p> User Name:
				<c:out value="${sessionScope.userName}"></c:out>
			</p>
			<p> Emp ID:
				<c:out value="${sessionScope.userData.employeeId}"></c:out>
			</p>
		</div>
		
		<%-- <c:set var = "roleList" scope="session" value = "${sessionScope.userData.roleList}"/>
		<c:forEach items="${roleList}" var="role">
			<c:out value="${role.role_name}"></c:out>
		</c:forEach> --%>

		<div id='cssmenu'>
			<ul>
				<li class='active has-sub'><a href='#'><span>Role
							Management</span></a>
					<ul>
						<li class='has-sub'><a href='#'><span>Create new
									role</span></a></li>
						<li class='has-sub'><a href='#'><span>View all
									roles</span></a></li>
						<li class='has-sub'><a href='#'><span>Delete role
							</span></a></li>
						<li class='has-sub'><a href='#'><span>Update role</span></a></li>
						<li class='has-sub'><a href='#'><span>Show
									features for role</span></a></li>
					</ul></li>
			</ul>

			<ul>
				<li class='active has-sub'><a href='#'><span>User
							Management</span></a>
					<ul>
						<li class='has-sub'><a href='#'><span>Create user</span></a></li>
						<li class='has-sub'><a href='#'><span>View user
									status</span></a></li>
						<li class='has-sub'><a href='#'><span>View
									training information </span></a></li>
						<li class='has-sub'><a href='#'><span>View project
									allocation</span></a></li>
						<li class='has-sub'><a href='#'><span>Delete user</span></a></li>
						<li class='has-sub'><a href='#'><span>Update user</span></a></li>
						<li class='has-sub'><a href='#'><span>Upload
									project allocation details</span></a></li>
					</ul></li>
			</ul>

			<ul>
				<li class='active has-sub'><a href='#'><span>On
							Boarding</span></a>
					<ul>
						<li class='has-sub'><a href='#'><span>Upload
									documents</span></a></li>
						<li class='has-sub'><a href='#'><span>Update
									document verification status</span></a></li>
					</ul></li>
			</ul>

		</div>
	</form>
</body>
</html>
