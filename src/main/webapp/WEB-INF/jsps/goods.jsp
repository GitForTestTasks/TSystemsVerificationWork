<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-16">
    <title>Insert title here</title>
</head>
<body>

<h2>Results</h2>

<core:forEach var="row" items="${goods}">


    <div class="good">
        <div class="title">${row.title} </div>
        <div class="price">${row.price}</div>
        <div class="size">${row.size}</div>
        <div class="category">${row.category}</div>
        <div class="weight">${row.weight}</div>
        <div class="characteristics">${row.characteristics}</div>
        <div class="count">${row.count}</div>
    </div>


</core:forEach>


<core:forEach begin="1" end="${numberOfPages}" var="val">
    <div class="pageNumbers">
        <a href="${pageContext.request.contextPath}/goods/${val}">page: ${val}</a>
    </div>
</core:forEach>



</body>
</html>