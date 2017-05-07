<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='aordershistory-wraper'>
    <h1>Admin Orders History</h1>
    <table class='table table-striped table-hover'>
        <thead>
        <tr>
            <th>Order Id</th>
            <th>User</th>
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

        <core:forEach var="row" items="${orders}">
            <tr class="active">
                <td>${row.orderId}</td>

                <td>${row.clientId.email}</td>

                <td>
                        ${row.clientAddressId.country}
                        ${row.clientAddressId.city}
                        ${row.clientAddressId.street}
                        ${row.clientAddressId.houseNumber}
                        ${row.clientAddressId.apartment}
                </td>

                <td>${row.deliveryMethod}</td>
                <td>${row.paymentMethod}</td>

                <td class="${row.paymentStatus}">
                        ${row.paymentStatus}
                </td>
                <td class="${row.orderStatus}">
                        ${row.orderStatus}
                </td>
                <td>${row.dateOfCreation}</td>
                <td>

                    <a href='${pageContext.request.contextPath}/admin/editorder?orderId=${row.orderId}'
                       class='btn btn-primary btn-xs'>
                        Edit
                    </a>
                </td>
            </tr>
        </core:forEach>

        </tbody>
    </table>

    <ul class="pagination">
        <core:forEach begin="1" end="${numberOfPages}" var="val">
            <li>
                <a href="${pageContext.request.contextPath}/admin/orders?pageid=${val}">
                        ${val}
                </a>
            </li>
        </core:forEach>
    </ul>

    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/ordersadmin.css'/>

</div>


