<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create Good</title>
</head>
<body>

<div class="forms">
    <form:form name="register" method="post" action="${pageContext.request.contextPath}/admin/creategood"
               commandName="good">
        <fieldset>
            <legend>Create good</legend>
            <div class="formsRow">
                <div class="formsFields">Title</div>
                <div class="formsRow"><form:input path="title" required="true" type="text" name="title"/></div>
            </div>
            <div class="error"><form:errors path="title"/></div>
            <div class="formsRow">
                <div class="formsFields">Price</div>
                <div class="formsRow"><form:input path="price" required="true" pattern="\d+(\.\d{4})?" type="text"
                                                  name="price"/></div>
            </div>
            <div class="error"><form:errors path="price"/></div>
            <div class="formsRow">
                <div class="formsFields">Category</div>
                <div class="formsRow"><form:input path="category" type="text" name="category"/></div>
            </div>
            <div class="error"><form:errors path="category"/></div>
            <div class="formsRow">
                <div class="formsFields">Weight</div>
                <div class="formsRow"><form:input path="weight" pattern="\d+(\.\d{3})?" type="text"
                                                  name="weight"/></div>
            </div>
            <div class="error"><form:errors path="weight"/></div>
            <div class="formsRow">
                <div class="formsFields">Size</div>
                <div class="formsRow"><form:input path="size" type="text" name="size"/></div>
            </div>
            <div class="error"><form:errors path="size"/></div>
            <div class="formsRow">
                <div class="formsFields">Count</div>
                <div class="formsRow"><form:input path="count" required="true" pattern="^[ 0-9]+$" type="text"
                                                  name="count"/></div>
            </div>
            <div class="error"><form:errors path="count"/></div>
            <div class="formsRow">
                <div class="formsFields">Characteristics</div>
                <div class="formsRow"><form:textarea path="characteristics" NAME="characteristics" ROWS="5"
                                                     COLS="25"/></div>
            </div>
            <div class="error"><form:errors path="characteristics"/></div>
            <div class="formsRow">
                <div class="formsFields">&nbsp;</div>
                <div class="formsRow"><INPUT class="button" type="submit" name="submit" value="Create Good"></div>
            </div>
            <div class="formsRow"></div>
        </fieldset>
    </form:form>
</div>


</body>
</html>
