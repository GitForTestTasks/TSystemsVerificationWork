<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="${pageContext.request.contextPath}/admin/createcategory">Create New Category</a>

<core:forEach var="row" items="${category}">
    <div class="categories">
        <div class="rowCategory">${row.name}</div>
        <div class="rowCategory">
            <a href="${pageContext.request.contextPath}/admin/createcategory?categoryId=${row.categoryId}">Change</a>
        </div>
        <div class="rowCategory">
            <a href="${pageContext.request.contextPath}/admin/createcategory?delete=${row.categoryId}">Delete</a>
        </div>
    </div>
</core:forEach>