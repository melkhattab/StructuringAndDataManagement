<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
							<form:form modelAttribute="roleData" method="post" action="createRole" >
								<div class="form-group">
									<label for="laboratoryName">Role *</label> 
									<form:input path="role" class="form-control" placeholder="Insérez le libellé du role" />
								</div>
								<div class="form-group"> 
									<label for="description">Description *</label> 
									<form:textarea path="description" rows="5" cols="30" class="form-control" placeholder="Description du role"/>
								</div>
								<div class="form-group"> 
									<label for="read">Lecture</label> 
									<form:checkbox path="read" />
									<label for="read">Ecriture permission</label> 
									<form:checkbox path="write" />
									<label for="read">Modification permission</label> 
									<form:checkbox path="update" />
									<label for="read">deleting permission</label> 
									<form:checkbox path="delete" />
								</div>
								<div>
									<button type="submit" class="btn btn-primary mb-2">Ajouter Role</button>
									<button type="reset" class="btn btn-primary mb-2">Réinitialiser</button>
								</div>
						</form:form>
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