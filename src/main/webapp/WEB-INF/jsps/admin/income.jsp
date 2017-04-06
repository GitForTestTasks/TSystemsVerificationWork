<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="statistics-wraper">
    <h1>Sales statistics: Earnings</h1>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/admin/toptengoods">Top ten sales</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/toptenclients">Top ten clients</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/admin/admin">Go Back</a>
        </li>
    </ul>

    <table class='table table-striped table-hover'>
        <thead>
        <tr>
            <th>Period</th>
            <th>Amount</th>
        </tr>
        </thead>
        <tbody>

        <tr class="active">
            <td>Earnings per Month</td>
            <td><core:out value="${income.get(0)}"/></td>
        </tr>
        <tr class="active">
            <td>Earnings per Week</td>
            <td><core:out value="${income.get(1)}"/></td>
        </tr>

        </tbody>
    </table>

</div>