<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	  
</h1>
<f:form modelAttribute="loginDetails" method="post" action="signIn">
	<div class="row">
	<h2> Login : </h2>
		<div> 
			Email : <f:input path="email"/>
		</div>
		<div>
			Password : <f:password path="password"/>
		</div>
		<div>
			<a href="createAccount"> create account</a>
		</div>
		</div>
		<div>
			<input type="submit" value="Se connecter"/>
		</div>
	</div>
</f:form>
	
</body>
</html>
