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
							Bonjour! vous êtes Mr : ${userSession.firstName } ${ userSession.lastName }	(id) ${userSession.id }
						</h4>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Corpus Name</th>
									<th>Description</th>
									<th>Laboratory</th>
									<th>Created by</th>
									<th>Actions </th>
							   </tr>
							</thead>
							<tbody>
								<c:forEach items="${allCorpus}" var="corpus">
									<tr>
										<td>${corpus.name }</td>
										<td>${corpus.description }</td> 
										<td>${corpus.name }</td> 	<!-- Assoc labs -->
										<td> 
											EL KHATTAB Mahmoud		<!-- created by -->
										</td>
										<td>
											
											<a href="<c:url value='updateCorpus/${corpus.idCorpus}' />" >
												<img  width="23" alt="icon not found" src="<%=request.getContextPath()%>/resources/img/update.png">
											</a>
											<a href="<c:url value='deleteCorpus/${corpus.idCorpus}' />">
												<img alt="icon not found" width="25" src="<%=request.getContextPath()%>/resources/img/delete.png">
											</a>
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