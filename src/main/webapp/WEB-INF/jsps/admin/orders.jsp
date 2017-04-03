<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>


<core:forEach var="row" items="${orders}">
    <p>${row}</p>

    <form name="editOrder" method="get"
          action="${pageContext.request.contextPath}/admin/editorder">
        <INPUT name="orderId" type="hidden" value="${row.orderId}"/>
        <INPUT class="button" type="submit" name="submit" value="Edit">
    </form>
</core:forEach>

<core:forEach begin="1" end="${numberOfPages}" var="val">
    <div class="pageNumbers">
        <a href="${pageContext.request.contextPath}/admin/orders?pageid=${val}">page: ${val}</a>
    </div>

</core:forEach>
