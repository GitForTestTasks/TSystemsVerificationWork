<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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