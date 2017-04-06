<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class='navbar navbar-default'>
    <div class='container-fluid'>
        <div class='collapse navbar-collapse' id='bs-example-navbar-collapse-2'>

            <ul class='nav navbar-nav'>
                <li><a href='${pageContext.request.contextPath}/'>Home</a></li>
                <li><a href='${pageContext.request.contextPath}/goods'>Products</a></li>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href='${pageContext.request.contextPath}/admin/orders'>Orders History</a></li>
                    <li><a href='${pageContext.request.contextPath}/admin/categories'>Categories</a></li>
                    <li><a href='${pageContext.request.contextPath}/admin/creategood'>Add product</a></li>
                    <li><a href='${pageContext.request.contextPath}/admin/admin'>Statistics</a></li>
                </sec:authorize>

            </ul>

            <ul class='nav navbar-nav navbar-right'>

                <li><a href='${pageContext.request.contextPath}/cart'>Cart <span class='badge cart-size'>
                        <core:out value="${cartSize}" default="0"/>
                    </span></a></li>

                <sec:authorize access="isAuthenticated()">
                    <li><a href='${pageContext.request.contextPath}/account/orders'>You Orders</a></li>
                    <li><a href='${pageContext.request.contextPath}/account/profile'>Profile</a></li>
                    <li><a href='${pageContext.request.contextPath}/account/clientaddresses'>Addresses</a></li>
                    <li><a href='${pageContext.request.contextPath}/logout'>Logout</a></li>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <li>
                        <a href="${pageContext.request.contextPath}/login">Login</a>
                    </li>
                    <li><a href='${pageContext.request.contextPath}/account/createaccount'>Registration</a></li>
                </sec:authorize>

            </ul>

        </div>
    </div>
</nav>