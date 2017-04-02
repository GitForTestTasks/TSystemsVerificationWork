<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="forms">
    <form:form id="details" name="profile" method="post" action="${pageContext.request.contextPath}/account/profile"
               commandName="client" modelAttribute="client">
        <fieldset>
            <legend>Edit Account</legend>
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
                <div class="formsFields">Password</div>
                <div class="formsRow"><input id="password" required type="password" name="password"/>
                </div>
            </div>
            <div class="error"><form:errors path="password"/></div>
            <div class="formsRow">
                <div class="formsFields">Confirm Password</div>
                <div class="formsRow"><input id="confirmPassword" required type="password" name="confirmPassword"/>
                </div>
            </div>
            <form:input id="email" path="email" type="hidden" name="email"/>

            <div id="matchpass"></div>
            <div class="formsRow">
                <div class="formsFields">&nbsp;</div>
                <div class="formsRow"><INPUT class="button" type="submit" name="submit" value="Edit Account"></div>
            </div>
            <div class="formsRow"></div>
        </fieldset>
    </form:form>
</div>

<a href="${pageContext.request.contextPath}/account/clientaddresses">Client address information</a>
<a href="${pageContext.request.contextPath}/account/orders">My orders</a>


<core:if test="${state}">
    <h1>Success</h1>
</core:if>