<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>


<div class='orders-wraper'>
    <h1>Your Orders</h1>
    <table class='table table-striped table-hover'>
        <thead>
        <tr>
            <th>Order Id</th>
            <th>Client Address</th>
            <th>Delivery Method</th>
            <th>Payment Method</th>
            <th>Payment Status</th>
            <th>Order StatusId</th>
            <th>Date Of Creation</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <core:forEach var="entry" items="${orders}">
            <tr class="active">
                <td><core:out value="${entry.orderId}"/></td>
                <td><core:out value="${entry.clientAddressId}"/></td>
                <td><core:out value="${entry.deliveryMethod}"/></td>
                <td><core:out value="${entry.paymentMethod}"/></td>

                <td class="${entry.paymentStatus}"><core:out value="${entry.paymentStatus}"/></td>
                <td class="${entry.orderStatus}"><core:out value="${entry.orderStatus}"/></td>
                <td><core:out value="${entry.dateOfCreation}"/></td>

                <td>

                    <a class="product-btn-add btn btn-default btn-xs"
                       href="${pageContext.request.contextPath}/account/orderinfo?orderId=${entry.orderId}">
                        View order</a>

                </td>

            </tr>
        </core:forEach>
        </tbody>
    </table>

    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/ordersadmin.css'/>
</div>