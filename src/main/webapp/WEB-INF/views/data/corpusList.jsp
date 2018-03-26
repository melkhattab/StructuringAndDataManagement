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
		Liste des utilisateurs: 
	</h2>
	<table border="1">
		<tr>
			<td>Nom du corpus</td>
			<td>Description</td>
			<td>Laboratoires associés</td>
			<td>Créé par </td>
		</tr>
	   <c:forEach items="${users}" var="user">
		<tr>
			<td>${user.lastName }</td>
			<td>${user.firstName }</td>
			<td>${user.email }</td>
			<td> 
				<c:choose>
				    <c:when test="${empty user.laboratory.name}">
				        Lab non renseigné
				    </c:when>    
				    <c:otherwise>
				       ${user.laboratory.name}  
				    </c:otherwise>
				</c:choose>
			</td>
		</tr>
	   </c:forEach>
	</table>
</body>
</html>
