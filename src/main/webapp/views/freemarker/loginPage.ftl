<!DOCTYPE html>
<html>
	<head>
	 <meta charset="utf-8">
     <meta http-equiv="X-UA-Compatible" content="IE=edge">
     <meta name="viewport" content="width=device-width, initial-scale=1">
	 <#include "*/includes.ftl">
	 <link rel="stylesheet" type="text/css" href="./resources/css/login.css">
	</head>
	
	<body>
		<div class="loginContainer">
	      <form class="form-signin" action="./login" method="POST">
	        <h2 class="form-signin-heading">Please sign in</h2>
	        <label for="inputEmail" class="sr-only">Email address</label>
	        <input type="text" id="usernameInput" name="username" class="form-control" placeholder="Username" autofocus="" required>
	        <label for="inputPassword" class="sr-only">Password</label>
	        <input type="password" id="passwordInput" name="password" class="form-control" placeholder="Password" required>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		      <#if loginError??>
		      	<p class="loginError">Invalid username or password combination.</p>
		      </#if>
	      </form>
	    </div>
	</body>
</html>