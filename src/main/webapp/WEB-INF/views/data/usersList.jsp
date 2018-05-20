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
			<div class="row" style="background-color: #ecf2f8;">
				<%@include file="../includes/sidemenubar.jsp" %>
				
				<div class="col-sm-8 col-md-8 col-lg-8 col-xl-9 ">
					<div class="content">
						<h4>
							Bonjour! vous êtes Mr : ${userSession.firstName } ${ userSession.lastName }	
						</h4>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Nom</th>
									<th>Prénom</th>
									<th>Email</th>
									<th>Laboratoire</th>
									<th>Role</th>
									<th>Permissions</th>
									<th>Modifier/Supprimer</th>
							   </tr>
							</thead>
							<tbody>
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
										<td> 
											<c:choose>
											    <c:when test="${empty user.role.libelle}">
											        Role non renseigné
											    </c:when>
											    <c:otherwise>
											       ${user.role.libelle}  
											    </c:otherwise>
											</c:choose>
										</td>
										<td>RWUD</td>
										<td>
											<a href="updateUser">Modifier</a>--
											<a href="<c:url value='deleteUser/${user.id}' />" >Supprimer</a>
										</td>
									</tr>
								   </c:forEach>								
							</tbody>
						</table>
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