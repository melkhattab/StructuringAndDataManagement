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
			<div class="row navbar">
				<%@include file="../includes/navbar.jsp" %>  
			</div>
			<div class="row">
				
				<%@include file="../includes/sidemenubar.jsp" %>

				<div class="col-sm-8 col-md-8 col-lg-8 col-xl-8 ">
					<div>
						<a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Hide menu</a>
					</div>
					<div class=" content">
						<div class="newCorpusForm">
							<f:form modelAttribute="corpus" method="post" action="createCorpus" >
								<div class="form-group">
									<label for="corpusName">Nom</label> 
									<f:input path="corpusName" class="form-control" placeholder="Nom du corpus" />
								</div>
								<div class="form-group"> 
									<label for="description">Description</label> 
									<f:textarea path="description" rows="5" cols="30" class="form-control" placeholder="Description du corpus"/>
								</div>
								<div class="form-group">
									<label for="corpusCapacity">Capacité</label> 
									<f:input path="capacity" class="form-control" placeholder="Capacité du corpus" />
								</div>
								<div>
									<button type="reset" class="btn btn-primary mb-2">Réinitialiser</button>
									<button type="submit" class="btn btn-primary mb-2">Ajouter</button>
								</div>
						</f:form>
						</div>
					</div>
					
				</div>
			</div>
			<div class="footer">
				<%@include file="../includes/footer.jsp" %>
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