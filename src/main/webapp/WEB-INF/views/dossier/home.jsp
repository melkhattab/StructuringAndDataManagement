<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Bonjour! vous êtes Mr : ${user.email } ${ user.password }	
	  
</h1>
	<a href="info"> afficher les infos</a>
</body>
</html>
