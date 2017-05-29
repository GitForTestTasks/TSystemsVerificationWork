<div class="login-wraper">
    <div>
        <form name='f' action='${pageContext.request.contextPath}/login' method='POST'>
            <div class='modal-content'>
                <div class='modal-header'>
                    <legend class='modal-title'>
                        Login
                    </legend>
                </div>

                <div class='modal-body form-horizontal'>

                    <div class='form-group'>
                        <label class='col-lg-2 control-label'>
                            Email
                        </label>
                        <div class='col-lg-10'>
                            <input class='form-control'
                                   placeholder='Email'
                                   type='email'
                                   name='username'
                                   required=''/>
                        </div>
                    </div>

                    <div class='form-group'>
                        <label class='col-lg-2 control-label'>
                            Password
                        </label>
                        <div class='col-lg-10'>
                            <input class='form-control'
                                   placeholder='Password'
                                   type='password'
                                   name='password'
                                   required=''/>
                            <div class="checkbox">
                                <label>
                                    <input class="loginInput" type='checkbox' name='_spring_security_remember_me'/>
                                    Remember me
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class='form-group'>
                        <label class='col-lg-2 control-label'>
                        </label>
                        <div class='col-lg-10 modal-login-erro text-danger'>
                            ${SPRING_SECURITY_LAST_EXCEPTION.message}
                        </div>
                    </div>
                </div>

                <div class='modal-footer'>
                    <div class='form-group form-horizontal'>
                        <div class='col-lg-10 col-lg-offset-2'>
                            <input class='btn btn-primary' name="submit" type="submit" value="Login"/>
                        </div>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>