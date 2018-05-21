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
						<p> Bienvenue ${userSession.firstName } ${userSession.lastName } sur la plateforme de gestion et de structuration de données <p>
						<table>
						<tbody>
								<tr>
								<td class="concordgeneral" width="33%" align="center">
									&nbsp;<br>
									<a href="flaubert_romans/">Flaubert -- oeuvres en prose</a>
									<br>&nbsp;
								</td>
					
								<td class="concordgrey" width="33%" align="center">
									&nbsp;<br>
									<a href="jeunesse_4/">Littérature jeunesse -- Master 2016-17</a>
									<br>&nbsp;
								</td>
					
								<td class="concordgeneral" width="33%" align="center">
									&nbsp;<br>
									<a href="marivaux_m1/">Marivaux M1 2017-2018 neuf pièces</a>
									<br>&nbsp;
								</td>
							</tr>
							<tr>
								<td class="concordgrey" width="33%" align="center">
									&nbsp;<br>
									<a href="moliere_beta_1/">moliere_beta_1</a>
									<br>&nbsp;
								</td>
					
								<td class="concordgeneral" width="33%" align="center">
									&nbsp;<br>
									<a href="moliere_beta_2/">Moliere -- quatorze pieces</a>
									<br>&nbsp;
								</td>
								<td class="concordgrey" width="33%" align="center">&nbsp;</td>
							</tr>		
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