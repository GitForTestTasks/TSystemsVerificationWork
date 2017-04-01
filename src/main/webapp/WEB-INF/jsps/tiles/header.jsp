<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="${pageContext.request.contextPath}/cart">Cart ( ${cartSize} )</a>

<sec:authorize access="isAuthenticated()">
    <p><a href="${pageContext.request.contextPath}/logout">logout</a></p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <p><a href="${pageContext.request.contextPath}/account/profile">profile</a></p>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
    <p><a href="${pageContext.request.contextPath}/login">login</a></p>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <p><a href="${pageContext.request.contextPath}/admin/admin">admin</a></p>
</sec:authorize>

<p><a href="${pageContext.request.contextPath}/goods?pageid=1">Catalogue</a></p>