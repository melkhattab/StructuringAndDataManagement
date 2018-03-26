<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
	<head>
		<title>Create Account</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mycss.css"/>
	</head>
	<body>
	<h3>
		  <c:if test="${not empty errorAccount}"> ${errorAccount }</c:if>
	</h3>
	<div class="newAccountForm">
		<f:form modelAttribute="userAccount" method="post" action="addAccount" >
				<div class="form-group">
					<label for="fiNamerst">Prénom  *</label> 
					<f:input path="firstName" class="form-control" placeholder="Insérez votre prénom" />
				</div>
				<div class="form-group"> 
					<label for="lastName">Nom  *</label> 
					<f:input path="lastName" class="form-control" placeholder="Insérez votre nom"/>
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
				<div class="form-group">
					<label for="email">Email  * </label> 
					<f:input path="email" class="form-control" placeholder="email@example.com"/>
				</div>
				<div class="form-group">
					<label for="password">Mot de passe * </label> 
					<f:password path="password"  class="form-control" placeholder="Mot de passe"/>
				</div>
				<div class="form-group">
					<label for="confirmpassword">Confirmez le mot de passe *  </label> 
					<f:password path="confPassword" class="form-control" placeholder="Confirmez votre mot de passe"/>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary mb-2">Ajouter</button>
				</div>
		</f:form>
	</div>
	</body>
</html>
