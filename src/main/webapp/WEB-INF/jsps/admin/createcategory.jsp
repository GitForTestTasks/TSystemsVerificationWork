<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="forms">
    <form:form id="details" name="createCategory" method="post"
               action="${pageContext.request.contextPath}/admin/createcategory"
               commandName="category">
        <fieldset>
            <legend>Create Category</legend>

            <core:if test="${fail}">
                <h1>That category already exists</h1>
            </core:if>
            <div class="formsRow">
                <div class="formsFields">First Name</div>
                <div class="formsRow"><form:input path="name" required="true" type="text" name="name"/></div>
            </div>
            <div class="error"><form:errors path="name"/></div>
            <form:input path="categoryId" type="hidden" name="categoryId"/>
            <div class="formsRow">
                <div class="formsFields">&nbsp;</div>
                <div class="formsRow"><INPUT class="button" type="submit" name="submit" value="Create Category"></div>
            </div>
            <div class="formsRow"></div>
        </fieldset>
    </form:form>
</div>
