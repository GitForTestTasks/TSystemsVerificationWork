<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<core:forEach var="entry" items="${goods}">
    <p>Good: <core:out value="${entry.key}"/></p>
    <p>Quantity: <core:out value="${entry.value}"/></p>
    <p><a href="${pageContext.request.contextPath}/cart?deleteItemFromCart=${entry.key.goodId}">Delete from cart</a></p>
</core:forEach>

<core:if test="${success}">
    <h1>Success</h1>
</core:if>

<core:if test="${cartSize > 0}">

    <form:form name="order" method="post" action="${pageContext.request.contextPath}/cart"
               commandName="order" cssClass="form-horizontal">
        <fieldset>
            <legend>Create order</legend>

            <form:select path="paymentMethod" multiple="false">
                <form:options/>
            </form:select>

            <form:select path="deliveryMethod" multiple="false">
                <form:options/>
            </form:select>

            <form:select path="clientAddressId" multiple="false">
                <form:options items="${clientAddresses}" itemLabel="country" itemValue="clientAddressId"/>
            </form:select>

            <form:errors path="paymentMethod"/>
            <form:errors path="deliveryMethod"/>
            <form:errors path="clientAddressId"/>

            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <input class="button btn btn-primary" type="submit" name="submit" value="Create Order">
                </div>
            </div>
        </fieldset>
    </form:form>


</core:if>
