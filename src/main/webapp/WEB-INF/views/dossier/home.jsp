<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Bonjour! vous êtes Mr : ${userForm.email } ${ userForm.password }	  
</h1>
	
</body>
</html>
