<%@include file="/WEB-INF/templates/common/taglibs.jsp" %>
<h1>Home</h1>
<p>This is the homepage. Here are the latest 5 offers:</p>
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
	<tr>
		<td colspan="5"><a href='<c:url value="/offers/list"></c:url>'
			class="btn btn-primary">View all..</a></td>
	</tr>
</table>