<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mycss.css"/>
</head>
<body>
<h3>
	  <c:if test="${not empty errorConnection}"> ${errorConnection }</c:if>
</h3>
	
			<f:form modelAttribute="loginDetails" method="post" action="signIn" >
			<h2> Login : </h2>
				<div class="form-group">
					<label for="email">Email  * </label> 
					<f:input path="email" class="form-control" placeholder="email@example.com"/>
				</div>
				<div>
					<label for="password">Mot de passe * </label> 
					<f:password path="password"  class="form-control" placeholder="Mot de passe"/>
				</div>
				<div>
					<a href="createAccount"> create account</a>
				</div>
				<div>
					<button type="submit" class="btn btn-primary mb-2">Login</button>
				</div>
			</f:form>
		
</body>
</html>
