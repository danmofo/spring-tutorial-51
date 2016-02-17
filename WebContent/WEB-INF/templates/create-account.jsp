<%@include file="/WEB-INF/templates/common/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Home</title>
	<%@include file="/WEB-INF/templates/common/styles.jsp" %>
</head>
<body>
	<div class="container">
		<h1>Create account</h1>
		<spring:form commandName="user" method="POST" action="/register">
			<div class="form-group">
				<label>Username: </label>
				<spring:input path="username" placeholder="" cssClass="form-control"
					type="text" />
				<spring:errors path="username" cssClass="alert alert-danger"></spring:errors>
			</div>

			<div class="form-group">
				<label>Password: </label>
				<spring:input path="password" placeholder="" cssClass="form-control"
					type="text" />
				<spring:errors path="password" cssClass="alert alert-danger"></spring:errors>
			</div>


			<input type="submit" class="btn btn-primary" value="Save" />
		</spring:form>

	</div>
</body>
</html>