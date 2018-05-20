<f:form modelAttribute="loginDetails" method="post" action="signIn" >	
	<table class="basicbox" style="margin:auto">
		<tbody>
			<tr>
				<td class="basicbox">Enter your username:</td>
				<td class="basicbox">
					<f:input path="email" name="username" width="30" class="form-control" placeholder="email@example.com"/>
				</td>
			</tr>
			<tr>
				<td class="basicbox">Enter your password:</td>
				<td class="basicbox">
					<f:password path="password"  name="password" width="100" class="form-control" placeholder="password"/>
				</td>
			</tr>
			<tr>
				<td class="basicbox">Tick here to stay logged in on this computer:</td>
				<td class="basicbox">
					<input type="checkbox" name="persist" value="1">
				</td>
			</tr>
			<tr>
				<td class="basicbox" align="right">
					<input type="submit" value="Click here to log in">
				</td>
				<td class="basicbox" align="left">
					<input type="reset" value="Clear form">
				</td>
			</tr>
		</tbody>
	</table>
</f:form>