<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>


<core:if test="${not empty errCode}">
    <h1 class="text-danger text-center">System Errors : ${errCode}</h1>
</core:if>

<core:if test="${empty errCode}">
    <h1 class="text-danger text-center">System Errors</h1>
</core:if>

<core:if test="${not empty errMsg}">
    <h2 class="text-danger text-center">${errMsg}</h2>
</core:if>


