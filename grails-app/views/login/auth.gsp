<g:set var='securityConfig' value='${applicationContext.springSecurityService.securityConfig}'/>
<html>
<head>
	<meta name="layout" content="${layoutUi}"/>
	<s2ui:title messageCode='spring.security.ui.login.title'/>
%{--	<asset:stylesheet src='spring-security-ui-auth.css'/>--}%
	<asset:stylesheet src='login.css'/>
</head>
<body>
<p/>
<div>
	<div class="login-inner">
		<s2ui:form type='login' focus='username'>
			<div class="sign-in">
				<h1>Welcome Back</h1>
				<h2>Enter your credentials to access your account.</h2>
				<div class="form-field">
					<input type="text" name="${securityConfig.apf.usernameParameter}" id="username" class='formLogin' size="20" placeholder="Enter your email"/>
				</div>
				<div class="form-field">
					<td><input type="password" name="${securityConfig.apf.passwordParameter}" id="password" class="formLogin" placeholder="Enter you password" size="20"/></td>
				</div>


				<s2ui:submitButton elementId='loginButton' messageCode='Sign in'/>
			</div>
		</s2ui:form>
	</div>
</div>
<script>
	var hiddenLoginBtn = document.getElementById("loginButton_submit");
	var loginBtn = document.getElementById("loginButton");
	hiddenLoginBtn.addEventListener("click", function (e) {
		e.preventDefault();
		loginBtn.click();
	});
</script>
</body>
</html>
