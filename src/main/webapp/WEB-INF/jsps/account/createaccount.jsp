<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create Account</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-3.2.0.min.js"></script>
    <script type="text/javascript">
        function onLoad() {

            $("#password").keyup(checkPasswordsMatch);
            $("#confirmPassword").keyup(checkPasswordsMatch);

            $("#details").submit(canSubmit);
        }

        function canSubmit() {
            var password = $("#password").val();
            var confirmpass = $("#confirmPassword").val();

            if(password != confirmpass) {
                return false;
            }
            else {
                return true;
            }
        }

        function checkPasswordsMatch() {
            var password = $("#password").val();
            var confirmpass = $("#confirmPassword").val();

            if (password.length > 3 || confirmpass.length > 3) {

                if (password == confirmpass) {
                    $("#matchpass").text("Passwords match.");
                    $("#matchpass").addClass("valid");
                    $("#matchpass").removeClass("error");
                } else {
                    $("#matchpass").text("Passwords do not match.");
                    $("#matchpass").addClass("error");
                    $("#matchpass").removeClass("valid");
                }
            }
        }

        $(document).ready(onLoad);
    </script>
</head>
<body>

<div class="forms">
    <form:form id="details" name="register" method="post" action="${pageContext.request.contextPath}/account/createuser"
               commandName="client">
        <fieldset>
            <legend>Create Account</legend>
            <div class="formsRow">
                <div class="formsFields">First Name</div>
                <div class="formsRow"><form:input path="firstName" required="true" type="text" name="firstName"/></div>
            </div>
            <div class="error"><form:errors path="firstName"/></div>
            <div class="formsRow">
                <div class="formsFields">Last Name</div>
                <div class="formsRow"><form:input path="lastName" required="true" type="text" name="lastName"/></div>
            </div>
            <div class="error"><form:errors path="lastName"/></div>
            <div class="formsRow">
                <div class="formsFields">BirthDate</div>
                <div class="formsRow"><form:input path="birthDate" type="text" name="birthDate"/></div>
            </div>
            <div class="error"><form:errors path="birthDate"/></div>
            <div class="formsRow">
                <div class="formsFields">E-mail</div>
                <div class="formsRow"><form:input path="email" required="true" type="text" name="email"/></div>
            </div>
            <div class="error"><form:errors path="email"/></div>
            <div class="formsRow">
                <div class="formsFields">Password</div>
                <div class="formsRow"><form:input id="password" path="password" required="true" type="text" name="password"/></div>
            </div>
            <div class="error"><form:errors path="password"/></div>
            <div class="formsRow">
                <div class="formsFields">Confirm Password</div>
                <div class="formsRow"><input id="confirmPassword" required type="text" name="confirmPassword"/></div>
            </div>
            <div id="matchpass"></div>
            <div class="formsRow">
                <div class="formsFields">&nbsp;</div>
                <div class="formsRow"><INPUT class="button" type="submit" name="submit" value="Create Account"></div>
            </div>
            <div class="formsRow"></div>
        </fieldset>
    </form:form>
</div>


</body>
</html>
