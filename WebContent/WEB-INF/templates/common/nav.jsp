<nav class="navbar navbar-default">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Offer listings</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/offers/list">List <span class="sr-only">(current)</span></a></li>
        <li><a href="/offers/create">Create</a></li>
        <sec:authorize access="hasRole('ADMIN')">
        	<li><a href="/admin/home">Admin</a></li>
        </sec:authorize>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/register">Create account</a></li>
        <sec:authorize access="!isAuthenticated()">
        	<li><a href="/login">Log in</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
	        <li>
		        <form method="post" action="/logout">
		        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		        	<button class="btn btn-default" type="submit">Log out</button>
		        </form>
		    </li>
        </sec:authorize>
        
        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<!-- Global messages -->
<c:if test="${not empty message}">
	<div class="container">
		<springTags:message code="${message}"></springTags:message>
	</div>
</c:if>	