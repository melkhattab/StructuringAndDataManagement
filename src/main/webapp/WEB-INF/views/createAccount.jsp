<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h3>
	  <c:if test="${not empty errorAccount}"> ${errorAccount }</c:if>
</h3>
<f:form modelAttribute="userAccount" method="post" action="addAccount">
	<div class="row">
		<div> 
			First name *  <f:input path="firstName"/>
		</div>
		<div> 
			Last Name  * <f:input path="lastName"/>
		</div>
		<div> 
			Email  * <f:input path="email"/>
		</div>
		<div>
			Password * <f:password path="password"/>
		</div>
		<div>
			Confirm Password * <f:password path="confPassword"/>
		</div>
		<div>
		<input type="submit" value="Create"/>
		</div>
	</div>
</f:form>
	
</body>
</html>
