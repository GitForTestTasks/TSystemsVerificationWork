<html>
<head><title>Login Page</title></head>
<body onload='document.f.username.focus();'>
<h3>Login with Username and Password SELFMADE</h3>
<div class="error">
    ${SPRING_SECURITY_LAST_EXCEPTION.message}
</div>
<form name='f' action='${pageContext.request.contextPath}/login' method='POST'>
    <div class=loginRows>
        <div class=loginFields>User:</div>
        <div class=loginFields><input class="loginInput" type='text' name='username' value=''></div>
    </div>
    <div class=loginRows>
        <div class=loginFields>Password:</div>
        <div class=loginFields><input class="loginInput" type='password' name='password'/></div>
    </div>
    <div class=loginRows>
        <div class=loginFields colspan='2'><input class="loginInput" name="submit" type="submit" value="Login"/></div>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<div><a href="${pageContext.request.contextPath}/account/createuser">Create new account</a> </div>
</body>
</html>