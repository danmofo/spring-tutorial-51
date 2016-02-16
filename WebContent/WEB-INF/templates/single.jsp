
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<c:import url="/WEB-INF/templates/common/includes.jsp"></c:import>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Home</title>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${empty offer}">
				<div class="alert alert-danger">
					<p>No offer was found for this ID.</p>
				</div>
			</c:when>
			<c:otherwise>
				<c:if test='${success}'>
					<div class="alert alert-success">
						<p>Offer was updated / added successfully.
					</div>
				</c:if>	
				<h1>
					Single offer:
					<c:out value="${offer.id}"></c:out>
				</h1>
				<p>
					Some information about the offer.. <a
						href='<c:url value="/"></c:url>'>Go back</a>
				</p>
				<table class="table">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Description</th>
						<th>Actions</th>
					</tr>
					<tr>
						<td><c:out value="${offer.id}"></c:out></td>
						<td><c:out value="${offer.name}"></c:out></td>
						<td><c:out value="${offer.email}"></c:out></td>
						<td><c:out value="${offer.text}"></c:out></td>
						<td><a href="<c:url value='/offers/edit/${offer.id}'></c:url>" class="btn btn-default">Edit this offer</a></td>
					</tr>
				</table>
			</c:otherwise>

		</c:choose>
	</div>
</body>
</html>