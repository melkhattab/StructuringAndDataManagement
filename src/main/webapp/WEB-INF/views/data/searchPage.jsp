<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
	<head>
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mycss.css"/>
	</head>
	<body >
				<h4>
					Bonjour! vous êtes Mr :${result } ${userSession.firstName } ${ userSession.lastName }	(id) ${userSession.id }
				</h4>
				
				<f:form modelAttribute="fileData" method="post" action="searchFiles">
					<div class="box-body">
						<div class="form-group">
							<f:input path="searchValue" class="form-control" placeholder="Recherche" />
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
				<c:choose>
					<c:when test="${not empty files}">
						<table class="table table-striped" style="border-width:3px; border-style:solid; border-color: #ffffff ">
							<thead>
								<tr style="border-color: inherit;">
									<th style="background-color: #00ffff;border-width:3px; border-style:solid; border-color: #ffffff;">No.</th>
									<th style="background-color: #00ffff;border-width:3px; border-style:solid; border-color: #ffffff;">File Name</th>
									<th style="background-color: #00ffff;border-width:3px; border-style:solid; border-color: #ffffff;">Diff ID.</th>
									<th style="background-color: #00ffff;border-width:3px; border-style:solid; border-color: #ffffff;">Solution</th>
							   </tr>
							</thead>
							<tbody>
							  	<c:forEach items="${queryResult}" var="item" varStatus="loop">
								 	<tr style="border-color: inherit;">
								 	<td style="background-color: #b0ffff; border-width:3px; border-style:solid; border-color: #ffffff;"><c:out value="${loop.index+1}"/></td>
										<td style="background-color: #b0ffff; border-width:3px; border-style:solid; border-color: #ffffff;"><a href="moreContext/${item.value.get(0)}">${item.value.get(1)}</a></td>
										<td style="background-color: #b0ffff; border-width:3px; border-style:solid; border-color: #ffffff;"><a href="file">${item.value.get(0)} - ${item.value.get(0)-20} - ${item.value.get(0)+20} </a></td>
										<td style="background-color: #b0ffff; border-width:3px; border-style:solid; border-color: #ffffff;">
												${item.value.get(4)} <b><a href="extendedContext?num=${loop.index+1}&name=${loop.index+1}&id=${item.value.get(0)}">
												${item.value.get(3)}
												</a></b> ${item.value.get(5)}
										</td>
									</tr>
									
							   </c:forEach>								
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<h4> Aucun document n'a été trouvé. </h4>
					</c:otherwise>
				</c:choose>
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