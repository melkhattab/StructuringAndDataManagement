<h2> Login : </h2>
<f:form modelAttribute="loginDetails" method="post" action="signIn" >	
	<div class="form-group">
		<label for="email">Email  * </label> 
		<f:input path="email" class="form-control" placeholder="email@example.com"/>
	</div>
	<div>
		<label for="password">Mot de passe * </label> 
		<f:password path="password"  class="form-control" placeholder="Mot de passe"/>
	</div>
	<div>
		<a href="createAccount"> create account</a>
	</div>
	<div>
		<button type="submit" class="btn btn-primary mb-2">Login</button>
	</div>
</f:form>