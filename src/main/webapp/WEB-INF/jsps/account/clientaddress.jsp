<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="forms">
    <form:form id="details" name="register" method="post"
               action="${pageContext.request.contextPath}/account/clientaddress"
               commandName="clientAddress">
        <fieldset>
            <legend>Client Address</legend>
            <div class="formsRow">
                <div class="formsFields">Country</div>
                <div class="formsRow"><form:input path="country" required="true" type="text" name="country"/></div>
            </div>
            <div class="error"><form:errors path="country"/></div>
            <div class="formsRow">
                <div class="formsFields">City</div>
                <div class="formsRow"><form:input path="city" required="true" type="text" name="city"/></div>
            </div>
            <div class="error"><form:errors path="city"/></div>
            <div class="formsRow">
                <div class="formsFields">Postindex</div>
                <div class="formsRow"><form:input path="postIndex" required="true" type="text" name="postIndex"/></div>
            </div>
            <div class="error"><form:errors path="postIndex"/></div>
            <div class="formsRow">
                <div class="formsFields">Street</div>
                <div class="formsRow"><form:input path="street" required="true" type="text" name="street"/></div>
            </div>
            <div class="error"><form:errors path="street"/></div>
            <div class="formsRow">
                <div class="formsFields">House Number</div>
                <div class="formsRow"><form:input path="houseNumber" required="true" type="houseNumber"
                                                  name="houseNumber"/></div>
            </div>
            <div class="error"><form:errors path="houseNumber"/></div>
            <div class="formsRow">
                <div class="formsFields">Apartment</div>
                <div class="formsRow"><form:input path="apartment" required="true" type="apartment"
                                                  name="apartment"/></div>
            </div>
            <div class="error"><form:errors path="apartment"/></div>
            <form:input type="hidden" name="clientAddressId" path="clientAddressId"/>
            <div class="formsRow">
                <div class="formsFields">&nbsp;</div>
                <div class="formsRow"><INPUT class="button" type="submit" name="submit" value="Change"></div>
            </div>
            <div class="formsRow"></div>
        </fieldset>
    </form:form>
</div>

<core:if test="${success}">
    <h1>Success</h1>
</core:if>

<a href="${pageContext.request.contextPath}/account/profile">Profile Information</a>