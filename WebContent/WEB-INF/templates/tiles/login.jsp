<%@include file="/WEB-INF/templates/common/taglibs.jsp"%>
<h1>Log in</h1>

<form method="POST" action="/login">

	<c:if test="${not empty param.error}">
		<div class="alert alert-danger">
			<p>Invalid username and / or password.</p>
		</div>
	</c:if>

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />

	<div class="form-group">
		<label>Username: </label> <input name="username"
			placeholder="Enter your username" class="form-control" type="text" />
	</div>

	<div class="form-group">
		<label>Password: </label> 
		<input name="password"
			placeholder="Enter your password"
			class="form-control"
			type="password" />
	</div>

	<div class="form-group">
		<div class="checkbox">
			<label> <input type="checkbox" name="remember-me">Remember me for next time?
			</label>
		</div>
	</div>

	<input type="submit" class="btn btn-primary" value="Log in" />
</form>