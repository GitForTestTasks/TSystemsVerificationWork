<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="statistics-wraper">
    <h1>Sales statistics: Top Customers</h1>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/admin/toptengoods">Top ten sales</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/admin">Go Back</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/income">Income</a>
        </li>
    </ul>

    <table class='table table-striped table-hover'>
        <thead>
        <tr>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Total bought</th>
        </tr>
        </thead>
        <tbody>

        <core:forEach var="row" items="${topClients}">
            <tr class="active">
                <td><core:out value="${row.email}"/></td>
                <td><core:out value="${row.firstName}"/></td>
                <td><core:out value="${row.lastName}"/></td>
                <td><core:out value="${row.total}"/></td>
            </tr>
        </core:forEach>


        </tbody>
    </table>

</div>