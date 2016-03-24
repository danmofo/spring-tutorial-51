<%@include file="/WEB-INF/templates/common/taglibs.jsp"%>
<h1>Create account</h1>
<spring:form commandName="user" method="POST" action="/register">
	<div class="form-group">
		<label>Username: </label>
		<spring:input path="username" placeholder="" cssClass="form-control"
			type="text" />
		<spring:errors path="username" cssClass="alert alert-danger"></spring:errors>
	</div>
	
	<div class="form-group">
		<label>Full name: </label>
		<spring:input path="name" placeholder="" cssClass="form-control"
			type="text" />
		<spring:errors path="name" cssClass="alert alert-danger"></spring:errors>
	</div>

	<div class="form-group">
		<label>Email: </label>
		<spring:input path="email" placeholder="" cssClass="form-control"
			type="text" />
		<spring:errors path="email" cssClass="alert alert-danger"></spring:errors>
	</div>

	<div class="form-group">
		<label>Password: </label>
		<spring:password path="password" placeholder="" cssClass="form-control" />
		<spring:errors path="password" cssClass="alert alert-danger"></spring:errors>
	</div>

	<input type="submit" class="btn btn-primary" value="Save" />
</spring:form>
