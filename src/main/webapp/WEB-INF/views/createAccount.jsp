<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	  
</h1>
<f:form modelAttribute="userInformation" method="post" action="addAccount">
	<div class="row">
		<div> 
			First name *  <f:input path="email"/>
		</div>
		<div> 
			Last Name  * <f:input path="email"/>
		</div>
		<div> 
			Email  * <f:input path="email"/>
		</div>
		<div>
			Password * <f:password path="password"/>
		</div>
		<div>
			Confirm Password * <f:password path="password"/>
		</div>
		<div>
		<input type="submit" value="Create"/>
		</div>
	</div>
</f:form>
	
</body>
</html>
