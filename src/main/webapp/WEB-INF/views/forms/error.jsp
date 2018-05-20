<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
	<head>
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mycss.css"/>
	</head>
	<body>
	<header></header>
	<aside> <%//@include file="../includes/sidemenubar.jsp" %></aside>
	
		<div class="container"> 			
			<table class="concordtable" width="100%">
				<tbody>
					<tr>
						<th colspan="3" class="concordtable">
							<div style="float: left;"><img src="css/img/logo_UAPV.png" height="80" border="0"></div>      Welcome to CQPweb!		</th>
					</tr>
					<tr>
					</tr>
					<tr class="basicbox">
						<td colspan="3" class="concordgeneral">
								<%@include file="../forms/loginForm.jsp" %>  
							<p align="center">	<a href="createAccount">Create account</a> | 
												<a href="#">Password forgotten</a>
							</p>
							<p>&nbsp;</p>
						</td>
					</tr>
					<tr>
						<th colspan="3" class="concordtable">
							Corpora available on this server (<a href="usr/index.php?thisQ=corpusAccess&amp;uT=y">click here to view your own corpus access privileges</a>)
						</th>
					</tr>
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
		
		<footer>
			<%@include file="../includes/footer.jsp" %>
		</footer>
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