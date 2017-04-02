<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="${pageContext.request.contextPath}/account/clientaddress"> Create new address </a>

<core:forEach var="entry" items="${addresses}">
    <core:out value="${entry}"/>
    <form action="${pageContext.request.contextPath}/account/clientaddress" method="get">
        <input type="hidden" name="clientAddressId" value="${entry.clientAddressId}"><br>
        <input type="submit" value="Edit">
    </form>
</core:forEach>