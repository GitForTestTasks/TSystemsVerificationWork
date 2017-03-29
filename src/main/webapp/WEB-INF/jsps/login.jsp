<h3>Login with Username and Password</h3>
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
        <div class=loginFields>Remember me:</div>
        <div class=loginFields><input class="loginInput" type='checkbox' name='_spring_security_remember_me'/></div>
    </div>
    <div class=loginRows>
        <div class=loginFields colspan='2'><input class="loginInput" name="submit" type="submit" value="Login"/></div>
    </div>
</form>
<div><a href="${pageContext.request.contextPath}/account/createaccount">Create new account</a> </div>
