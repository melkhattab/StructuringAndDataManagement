<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
	<head>
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap-toggle.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mycss.css"/>
	</head>
	<body>
		<div class="container"> 
			<div class="row navbar">
				<%@include file="../includes/navbar.jsp" %>  
			</div>
			<div class="row" style="background-color: #ecf2f8;">
				<div class="col-sm-2 col-md-2 col-lg-2 col-xl-2">
					<%@include file="../includes/sidemenubar.jsp" %>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6 col-xl-10 ">
					<div>
						<a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Hide menu</a>
					</div>
					<div class=" content">
						<div class="newAccountForm">
							<f:form modelAttribute="userAccount" method="post" action="addAccount" >
								<div class="box-body">
									<div class="form-group">
										<label for="fiNamerst">Prénom  *</label> 
										<f:input path="firstName" class="form-control" placeholder="Insérez votre prénom" />
									</div>
									<div class="form-group"> 
										<label class="control-label" for="lastName">Nom  *</label> 
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
								</div>
							</f:form>
						</div>
					</div>
					<div class="footer">
						<%@include file="../includes/footer.jsp" %>
					</div>
				</div>
			</div>
			</div>
	
		<!-- scripts -->
		<script src="<%=request.getContextPath()%>/resources/js/jquery-3.3.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/bootstrap-toggle.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/myjs/sidemenubar.js"></script>
		<!-- Menu Toggle Script -->
    <script>
    
    </script>
	</body>
	
</html>