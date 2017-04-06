<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='categories-wraper'>
    <div class='wraper-table-categories'>
        <h1>Categories</h1>
        <table class='table table-striped table-hover table-categories'>
            <thead class='header-categories'>
            <tr>
                <th>CategoryId</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody class='categories'>

            <core:forEach var="row" items="${category}">

                <tr class='categorie' ng-class='$odd ? "active" : "info"'>
                    <td class='CategoryId'>${row.categoryId}</td>
                    <td class='Name'>${row.name}</td>
                    <td class='btn-wraper' data='${row.categoryId}'>
                        <a href="${pageContext.request.contextPath}/admin/createcategory?delete=${row.categoryId}"
                           class='btn btn-warning btn-xs categories-btn categories-btn-del'>
                            Delete
                        </a>
                        <a href="${pageContext.request.contextPath}/admin/createcategory?categoryId=${row.categoryId}"
                           class='btn btn-primary btn-xs categories-btn categories-btn-edt'>
                            Edit
                        </a>
                    </td>
                </tr>

            </core:forEach>

            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/admin/createcategory">Create new Category</a>
    </div>
</div>

