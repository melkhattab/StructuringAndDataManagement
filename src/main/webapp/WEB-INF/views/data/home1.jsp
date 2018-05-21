<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
	<title>Espace d'administration</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mycss.css"/>
</head>
<body>
	<h2>
		Bonjour! vous êtes Mr : ${userSession.firstName } ${ userSession.lastName }	
	</h2>
	<h5>
		<a href="logout"> Sign up </a>
	</h5>
	<h5>
		<a href="addLab"> Add laboratory</a>
	</h5>
	<h5>
		<a href="addLab"> Add corpus</a>
	</h5>
	<h5>
		<a href="addFile"> Add file</a>
	</h5>
	<h5>
		<a href="addRole"> Add role</a>
	</h5>
	<hr/>
	<h5>Concultation</h5>
	<h5>
		<a href="usersList"> Users List</a>
	</h5>
</body>
</html>
