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
		<h1>Add a new offer</h1>

		<spring:form commandName="offer" method="POST" action="/offers/create">
			<div class="form-group">
				<label>Name: </label>
				<spring:input path="name" placeholder="" cssClass="form-control"
					type="text" />
				<br />
				<spring:errors path="name" cssClass="alert alert-danger"></spring:errors>
			</div>

			<div class="form-group">
				<label>Email: </label>
				<spring:input path="email" placeholder="" cssClass="form-control"
					type="text" />
				<br />
				<spring:errors path="email" cssClass="alert alert-danger"></spring:errors>
			</div>

			<div class="form-group">
				<label>Text: </label>
				<spring:input path="text" placeholder="" cssClass="form-control"
					type="text" />
				<br />
				<spring:errors path="text" cssClass="alert alert-danger"></spring:errors>
			</div>

			<input type="submit" class="btn btn-primary" value="Save" />
		</spring:form>

	</div>
</body>
</html>