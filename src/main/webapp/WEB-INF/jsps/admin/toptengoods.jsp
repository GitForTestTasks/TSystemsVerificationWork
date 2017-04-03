<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<core:forEach var="row" items="${topGoods}">
    <p>${row}</p>
</core:forEach>