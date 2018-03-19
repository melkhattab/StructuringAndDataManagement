<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h3>
	La liste des utilisateurs   
</h3>
	<c:forEach var="user" items="${listOfUsers}">
		<c:out value="${user.firstName}"/>
	</c:forEach>
</body>
</html>
