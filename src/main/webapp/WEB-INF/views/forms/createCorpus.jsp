<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
	<title>Formulaire d'ajout d'un Role</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mycss.css"/>
</head>
<body>
	<h3>
		  <c:if test="${not empty errorAccount}"> ${errorAccount }</c:if>
	</h3>
	
	<f:form modelAttribute="corpusData" method="post" action="addCorpus" >
			<div class="form-group">
				<label for="corpusName">Nom du corpus *</label> 
				<f:input path="role" class="form-control" placeholder="Insérez le libellé du role" />
			</div>
			<div class="form-group"> 
				<label for="description">Description *</label> 
				<f:textarea path="description" rows="5" cols="30" class="form-control" placeholder="Description du role"/>
			</div>
			<div class="form-group"> 
				<label for="laboratory">Laboratoire *</label>
				<f:select multiple="false" path="laboratories" class="form-control">
					<f:option value="NONE" label="--- Select ---" selected="true" />
			   		<f:option value="LIA" />
			   		<f:option value="LHA" />
			   		<f:option value="LMA" />
				</f:select>
			</div>
			<div>
				<button type="reset" class="btn btn-primary mb-2">Réinitialiser</button>
				<button type="submit" class="btn btn-primary mb-2">Ajouter Role</button>
			</div>
	</f:form>
</body>
</html>
