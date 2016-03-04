<%@include file="/WEB-INF/templates/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
	Default layout for all user-facing pages.

	Apache tiles documentation:
	https://tiles.apache.org/framework/tutorial/basic/pages.html
-->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
		<link rel="stylesheet" href='<c:url value="/s/core.css"></c:url>' />
		<link rel="stylesheet" href='<c:url value="/s/main.css"></c:url>' />
	</head>
	<body>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<div class="container">
			<tiles:insertAttribute name="content"></tiles:insertAttribute>
		</div>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</body>
</html>