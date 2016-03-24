<%@include file="/WEB-INF/templates/common/taglibs.jsp" %>
<c:choose>
	<c:when test="${empty offer}">
		<div class="alert alert-danger">
			<p>No offer was found for this ID.</p>
		</div>
	</c:when>
	<c:otherwise>
		<h1>
			Editing single offer:
			<c:out value="${offer.id}"></c:out>
			<a href="/offers/list">Go back</a>
		</h1>
		
		<spring:form commandName="offer" method="POST" action="/offers/edit/${offer.id}">
			<spring:input path="id" type="hidden" />
		
			<div class="form-group">
				<label>Text: </label>
				<spring:input path="text" placeholder="" cssClass="form-control" type="text" />
				<br/>
				<spring:errors path="text" cssClass="alert alert-danger"></spring:errors>
			</div>
		
			<input type="submit" class="btn btn-primary" value="Save" />
		</spring:form>
	</c:otherwise>
</c:choose>
