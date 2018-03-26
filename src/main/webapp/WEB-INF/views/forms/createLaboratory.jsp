<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
	<title>Ajout d'un Laboratoir</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mycss.css"/>
</head>
<body>
	<h3>
		  <c:if test="${not empty errorAccount}"> ${errorAccount }</c:if>
	</h3>
	
	<f:form modelAttribute="labData" method="post" action="addLaboratory" >
			<div class="form-group">
				<label for="laboratoryName">Nom du laboratoire *</label> 
				<f:input path="name" class="form-control" placeholder="Insérez le nom du laboratoire" />
			</div>
			<div class="form-group"> 
				<label for="lastName">Description *</label> 
				<f:textarea path="description" rows="5" cols="30" class="form-control" placeholder="Décrivez le laboratoire"/>
			</div>
			<div>
				<button type="reset" class="btn btn-primary mb-2">Réinitialiser</button>
				<button type="submit" class="btn btn-primary mb-2">Ajouter Laboratoire</button>
			</div>
	</f:form>
</body>
</html>
