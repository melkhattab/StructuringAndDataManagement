<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
	<head>
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mycss.css"/>
	</head>
	<body data-gr-c-s-loaded="true">
		
						<h4>
							Bonjour! vous êtes Mr :${result } ${userSession.firstName } ${ userSession.lastName }	(id) ${userSession.id }
						</h4>
						
						<table class="concordtable">
							<tbody>
								<tr>
									<th> Displaying extended context for query match in text <i>la_jalousie</i></th>
								</tr>
								<tr>
									<f:form modelAttribute="fileData" method="post" action="searchFiles" >
										<div class="box-body">
											<div class="form-group">
												<label for="corpus">Par : </label>									   		
												<f:select multiple="false" path="searchBy" class="form-control">
													<f:option value="Corpus" />
													<f:option value="Author" />
													<f:option value="Title" />
												</f:select>
											</div>							
											<div class="forsm-group">
												<button type="submit" class="btn btn-primary mb-2">Rechercher</button>
											</div>
										</div>
									</f:form>
								</tr>
								<tr>
									<td class="concordgeneral">
										<p style=" background-color:cyan;">
											<c:forEach items="${queryResult}" var="item" varStatus="loop">
												<c:if test="${loop.index+1 eq orderNumber}">
															
															${item.value.get(4)}
														<b> ${item.value.get(3)} </b>
															${item.value.get(5)}
															
												</c:if>
										   </c:forEach>	
											
										</p>
									</td>
								</tr>
							</tbody>
						</table>
					
					<div class="footer">
						
					</div>
				
		<!-- scripts -->
		
		<!-- Menu Toggle Script -->
    <script>
    
    </script>
	</body>
	
</html>