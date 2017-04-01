<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<core:forEach var="entry" items="${goods}">
    <p>Good: <core:out value="${entry.key}"/></p>
    <p>Quantity: <core:out value="${entry.value}"/></p>
    <p><a href="${pageContext.request.contextPath}/cart?deleteItemFromCart=${entry.key.goodId}">Delete from cart</a> </p>
</core:forEach>

<core:if test="${cartSize > 0}">

    <form:form name="creategood" method="post" action="${pageContext.request.contextPath}/admin/creategood"
               commandName="order" cssClass="form-horizontal">
        <fieldset>
            <legend>Create good</legend>

<%--            <div class="form-group">
                <label class="col-lg-2 control-label">Category</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="colour" type="text" name="colour"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="colour" cssClass="text-danger col-lg-10"/>
            </div>--%>

            <form:select path="paymentMethod">
                <form:options items="${paymentMethod}"/>
            </form:select>


<%--            <div class="form-group">
                <label class="col-lg-2 control-label">Category</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="colour" type="text" name="colour"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="colour" cssClass="text-danger col-lg-10"/>
            </div>

            <div class="form-group">
                <label class="col-lg-2 control-label">Category</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="colour" type="text" name="colour"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="colour" cssClass="text-danger col-lg-10"/>
            </div>--%>

            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <input class="button btn btn-primary" type="submit" name="submit" value="Create Good">
                </div>
            </div>
        </fieldset>
    </form:form>


</core:if>
