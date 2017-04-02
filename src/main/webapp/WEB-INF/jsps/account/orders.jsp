<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<core:forEach var="entry" items="${orders}">
    <core:out value="${entry}"/>
    <core:if test="${entry.paymentMethod == 'CARD'}">
        <form action="${pageContext.request.contextPath}/account/orders" method="post">
            <input type="hidden" name="clientAddressId" value="${entry.orderId}"><br>
            <input type="submit" value="Pay">
        </form>
    </core:if>
</core:forEach>