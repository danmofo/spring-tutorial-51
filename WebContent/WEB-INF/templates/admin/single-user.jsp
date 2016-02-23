<%@include file="/WEB-INF/templates/common/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Home</title>
	<%@include file="/WEB-INF/templates/common/styles.jsp" %>
</head>
<body>
	<%@include file="/WEB-INF/templates/common/nav.jsp" %>
	<div class="container">
		
	<c:if test="${not empty success}">
		<div class="alert alert-success">
			<p>User updated successfully.</p>
		</div>
	</c:if>
	
		<h1>
			Editing single user:
			<c:out value="${user.username}"></c:out>
			<a href="/admin/users">Go back</a>
		</h1>
		
		<spring:form commandName="user" method="POST" action="/admin/users/edit/${user.username}">				
			<div class="form-group">
				<label>Username: </label>
				<spring:input path="username" placeholder="" cssClass="form-control" type="text" />
				<br/>
				<spring:errors path="username" cssClass="alert alert-danger"></spring:errors>
			</div>
		
			<div class="form-group">
				<label>Password: </label>
				<spring:input path="password" placeholder="" cssClass="form-control" type="text" />
				<br/>
				<spring:errors path="password" cssClass="alert alert-danger"></spring:errors>
			</div>
		
			<div class="form-group">
				<label>Email: </label>
				<spring:input path="email" placeholder="" cssClass="form-control" type="text" />
				<br/>
				<spring:errors path="email" cssClass="alert alert-danger"></spring:errors>
			</div>
			
			<div class="form-group">
				<label>Enabled: </label>
				<spring:select path="enabled" placeholder="" cssClass="form-control">
					<spring:option value="true">true</spring:option>
					<spring:option value="false">false</spring:option>
				</spring:select>
				<br/>
				<spring:errors path="enabled" cssClass="alert alert-danger"></spring:errors>
			</div>
		
			<input type="submit" class="btn btn-primary" value="Save" />
		</spring:form>
	</div>
</body>
</html>