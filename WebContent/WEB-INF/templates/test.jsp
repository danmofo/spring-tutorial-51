<%@include file="/WEB-INF/templates/common/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Home</title>
	<%@include file="/WEB-INF/templates/common/styles.jsp" %>
</head>
<body>
	<div class="container">
		<h1>DataAccessException</h1>
		<div class="alert alert-danger">
			<p><c:out value="${error}"></c:out></p>
		</div>
	</div>
</body>
</html>