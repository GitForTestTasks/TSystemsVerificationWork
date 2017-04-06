<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class='col-lg-6 col-centered wraper-profile'>
    <div class='well bs-component'>
        <form:form class='form-horizontal' id="details" name="register" method="post"
                   action="${pageContext.request.contextPath}/account/createaccount"
                   commandName="client">

            <fieldset>
                <legend>
                    Profie
                </legend>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        E-mail
                    </label>
                    <div class='col-lg-10'>
                        <form:input
                                placeholder='E-mail'
                                cssClass="form-control"
                                path="email" required="true" type="email" name="email"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="email"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'> Password </label>
                    <div class='col-lg-10'>
                        <form:input path="password" required="true" type="password"
                                    name="password" placeholder='Password' id="Password" cssClass="form-control"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'></label>
                    <div class='col-lg-10'>
                        <form:errors path="password"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Comfirm
                    </label>
                    <div class='col-lg-10'>
                        <input class='form-control'
                               placeholder='Comfirm Password'
                               type='password'
                               name='Comfirm'
                               id='Comfirm'
                               required=''/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'></label>
                    <div class='col-lg-10'>
                    </div>
                </div>


                <div class='form-group'>
                    <label class='col-lg-2 control-label'> First Name </label>
                    <div class='col-lg-10'>
                        <form:input placeholder='First Name'
                                    cssClass="form-control"
                                    path="firstName" required="true" type="text" name="firstName"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'></label>
                    <div class='col-lg-10'>
                        <form:errors path="firstName"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'> Last Name </label>
                    <div class='col-lg-10'>
                        <form:input cssClass='form-control'
                                    placeholder='Last Name' path="lastName" required="true" type="text"
                                    name="lastName"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'></label>
                    <div class='col-lg-10'>
                        <form:errors path="lastName"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'> Birth Date </label>
                    <div class='col-lg-10'>
                        <form:input
                                cssClass='form-control'
                                placeholder='Day.Month.Year'
                                id='BirthDate'
                                pattern='(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d'
                                path="birthDate" type="text" name="birthDate"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'></label>
                    <div class='col-lg-10'>
                        <form:errors path="birthDate"/>
                    </div>
                </div>


                <div class='form-group'>
                    <div class='col-lg-10 col-lg-offset-2'>
                        <a href='/' class='btn btn-default'>Cancel</a>
                        <button type='submit' class='btn btn-primary'>Submit</button>
                    </div>
                </div>

            </fieldset>
        </form:form>
    </div>

    <link href='${pageContext.request.contextPath}/static/css/jquery-ui-1.12.1.custom.min.css' rel='stylesheet'
          type='text/css'/>
    <script src='${pageContext.request.contextPath}/static/script/jquery-ui-1.12.1.custom.min.js'></script>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/profile.css'/>
    <script src='${pageContext.request.contextPath}/static/script/profile.js'></script>

</div>
