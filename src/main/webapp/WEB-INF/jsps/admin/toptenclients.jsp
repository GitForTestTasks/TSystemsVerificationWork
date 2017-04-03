<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<core:forEach var="row" items="${topClients}">
    Email: <core:out value="${row.email}"/>
    FirstName: <core:out value="${row.firstName}"/>
    LastName: <core:out value="${row.lastName}"/>
    Total bought: <core:out value="${row.total}"/>
</core:forEach>