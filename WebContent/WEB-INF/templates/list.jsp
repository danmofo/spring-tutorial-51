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
		<h1>List page</h1>
		<p>
			This is the list page. Here are all the offers: <a
				href='<c:url value="/"></c:url>'>Go home</a>
		</p>
		<table class="table">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Description</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="offer" items="${offers}">
				<tr>
					<td><c:out value="${offer.id}"></c:out></td>
					<td><c:out value="${offer.name}"></c:out></td>
					<td><c:out value="${offer.email}"></c:out></td>
					<td><c:out value="${offer.text}"></c:out></td>
					<c:url value="/offers/view/${offer.id}" var="offerViewUrl" />
					<c:url value="/offers/edit/${offer.id}" var="offerEditUrl" />
					<td><a href='<c:out value="${offerViewUrl}"></c:out>'
						class="btn btn-primary">View</a> <a
						href='<c:out value="${offerEditUrl}"></c:out>'
						class="btn btn-default">Edit</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>