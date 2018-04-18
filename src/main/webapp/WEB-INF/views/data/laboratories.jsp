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
					<div class="content">
						<h4>
							Bonjour! vous êtes Mr : ${userSession.firstName } ${ userSession.lastName }	
						</h4>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Nom du Laboratoire</th>
									<th>Description</th>
									<th>Modifier/Supprimer </th>
							   </tr>
							</thead>
							<tbody>
								<c:forEach items="${laboratories}" var="laboratory">
									<tr>
										<td>${laboratory.name }</td>
										<td>${laboratory.description }</td>
										<td>
											<a href="updateLaboratory">Modifier</a>--
											<a href="<c:url value='deleteLaboratory/${laboratory.idLaboratory}' />" >Supprimer</a>
										</td>
									</tr>
								   </c:forEach>								
							</tbody>
						</table>
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