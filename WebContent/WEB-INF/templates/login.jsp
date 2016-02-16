<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<c:import url="/WEB-INF/templates/common/includes.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Home</title>
</head>
<body>
	<div class="container">
		<h1>Log in</h1>

		<form method="POST" action="/login">
			
			<c:if test="${not empty param.error}">
				<div class="alert alert-danger">
					<p>Invalid username and / or password.</p>
				</div>
			</c:if>
					
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="form-group">
				<label>Username: </label> <input name="username" placeholder="Enter your username"
					class="form-control" type="text" />
			</div>

			<div class="form-group">
				<label>Password: </label> <input name="password" placeholder="Enter your password"
					class="form-control" type="password" />
			</div>

			<input type="submit" class="btn btn-primary" value="Log in" />
		</form>

	</div>
</body>
</html>