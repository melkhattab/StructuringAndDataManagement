<hr>
<table class="concordtable" style="width:100%">
		<tbody><tr>
			<td align="left" class="cqpweb_copynote" width="33%">
				CERI © 2017-2018 - EL KHATTAB Mahmoud
			</td>
			
			<td align="right" class="cqpweb_copynote" width="33%">
				<c:choose> 
					<c:when test="${ not empty userSession}">
						You are logged as ${userSession.firstName } ${userSession.lastName }
					</c:when>
					<c:otherwise>You are not logged in</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</tbody>
</table>
      