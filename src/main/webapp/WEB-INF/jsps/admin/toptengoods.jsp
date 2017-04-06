<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="statistics-wraper">
    <h1>Sales statistics: Top 10 Products</h1>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/admin/admin">Go Back</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/toptenclients">Top ten clients</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/income">Income</a>
        </li>
    </ul>

    <table class='table table-striped table-hover'>
        <thead>
        <tr>
            <th>Title</th>
            <th>Sold</th>
            <th>Left</th>
        </tr>
        </thead>
        <tbody>

        <core:forEach var="row" items="${topGoods}">
            <tr class="active">
                <td>${row.title}</td>
                <td>${row.quantitySum}</td>
                <td>${row.count}</td>
            </tr>
        </core:forEach>

        </tbody>
    </table>

</div>