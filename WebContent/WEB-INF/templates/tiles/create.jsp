<%@include file="/WEB-INF/templates/common/taglibs.jsp"%>
<h1>Add a new offer</h1>

<spring:form commandName="offer" method="POST" action="/offers/create">

	<div class="form-group">
		<label>Text: </label>
		<spring:input path="text" placeholder="" cssClass="form-control"
			type="text" />
		<spring:errors path="text" cssClass="alert alert-danger"></spring:errors>
	</div>

	<input type="submit" class="btn btn-primary" value="Save" />
</spring:form>
