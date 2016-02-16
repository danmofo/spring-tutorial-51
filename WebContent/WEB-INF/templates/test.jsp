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
		<h1>DataAccessException</h1>
		<div class="alert alert-danger">
			<p><c:out value="${error}"></c:out></p>
		</div>
	</div>
</body>
</html>