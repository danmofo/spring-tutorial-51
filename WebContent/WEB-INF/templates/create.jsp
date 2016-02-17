<%@include file="/WEB-INF/templates/common/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<%@include file="/WEB-INF/templates/common/styles.jsp" %>
</head>
<body>
	<%@include file="/WEB-INF/templates/common/nav.jsp" %>
	<div class="container">
		<h1>Add a new offer</h1>

		<spring:form commandName="offer" method="POST" action="/offers/create">
			<div class="form-group">
				<label>Name: </label>
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
				<label>Text: </label>
				<spring:input path="text" placeholder="" cssClass="form-control"
					type="text" />
				<spring:errors path="text" cssClass="alert alert-danger"></spring:errors>
			</div>

			<input type="submit" class="btn btn-primary" value="Save" />
		</spring:form>

	</div>
</body>
</html>