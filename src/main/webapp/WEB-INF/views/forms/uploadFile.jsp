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
							
							<f:form modelAttribute="fileData" method="POST" action="addFile" enctype="multipart/form-data">
								<div class="box-body">
									<div class="form-group">
										<label for="fileName"> </label> 
										<f:input type="text" name="name" path="title" class="form-control" placeholder="Titre..." />
									</div>
									<div class="form-group">
										<label for="fileName"></label> 
										<f:input type="text" name="name" path="author" class="form-control" placeholder="Auteur " />
									</div>
									<div class="form-group">
										<label for="fileName"></label> 
										<f:input type="text" name="name" path="date" class="form-control" placeholder="Date de création" />
									</div>
									<div class="form-group">
										<label for="fileName"></label> 
										<f:input type="text" name="name" path="nbr" class="form-control" placeholder="Nombre de pages" />
									</div>
									<div class="form-group">
										<f:select multiple="false" path="selectedCorpus" class="form-control">
									   		<c:forEach items="${fileData.corpus}" var="corpus">
											   <f:option value="${corpus.name}" />
											</c:forEach>
										</f:select>
									</div>
									<div class="form-group"> 
										 
										<f:textarea path="description" rows="5" cols="30" class="form-control" placeholder="Description"/>
									</div>
									<div class="form-group">
										<label for="fileName"></label> 
										<input type="file" name="file" class="form-control" placeholder="Selectionnez le fichier">
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-primary mb-2">Ajouter</button>
									</div>
								</div>
							</f:form>
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