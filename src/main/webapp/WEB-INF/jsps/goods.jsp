<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Results</h2>

<form method="get" action="${pageContext.request.contextPath}/goods" >
    Brand:<br>
    <input type="text" name="brand">
    <br>
    Colour:<br>
    <input type="text" name="colour">
    <INPUT class="button" type="submit" name="submit" value="search"></div>
</form>

<core:forEach var="row" items="${goods}">
    <div class="good panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title title">${row.title}</h3>
        </div>
        <div class="panel-body">

            <table class="table table-striped table-hover ">
                <tbody>
                <tr class="active">
                    <td>${row.price}</td>
                </tr>
                <tr>
                    <td>${row.size}</td>
                </tr>
                <tr class="active">
                    <td>${row.category}</td>
                </tr>
                <tr>
                    <td>${row.weight}</td>
                </tr>
                <tr class="active">
                    <td>${row.count}</td>
                </tr>
                <tr>
                    <td>${row.brand}</td>
                </tr>
                <tr class="active">
                    <td>${row.colour}</td>
                </tr>
                <tr>
                    <td><a class="buy" href="${pageContext.request.contextPath}/buygood?goodId=${row.goodId}&quantity=1">Add to
                        cart</a></td>
                </tr>
                <img src="${pageContext.request.contextPath}/static/images/${row.filePath}"/>
                </tbody>
            </table>
        </div>
    </div>
</core:forEach>

<core:forEach begin="1" end="${numberOfPages}" var="val">
    <div class="pageNumbers">
        <a href="${pageContext.request.contextPath}/goods?pageid=${val}">page: ${val}</a>
    </div>
</core:forEach>

