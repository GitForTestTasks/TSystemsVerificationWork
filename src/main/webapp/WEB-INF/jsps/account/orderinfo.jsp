<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="wraper-admin-edit-order">
    <h4>Order</h4>
    <table class="table table-striped table-hover ">
        <thead>
        <tr>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr class="active">
            <td>Payment method</td>
            <td>
                <core:out value="${order.paymentMethod}"/>
            </td>
        </tr>
        <tr class="active">
            <td>Delivery method</td>
            <td>
                <core:out value="${order.deliveryMethod}"/>
            </td>
        </tr>
        <tr class="active">
            <td>Payment status</td>
            <td>
                <core:out value="${order.paymentStatus}"/>
            </td>
        </tr>
        <tr class="active">
            <td>Date Of Creation</td>
            <td>${order.dateOfCreation}</td>
        </tr>
        </tbody>
    </table>

    <h4>Address</h4>
    <table class="table table-striped table-hover ">
        <thead>
        <tr>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr class="active">
            <td>Country</td>
            <td>${address.country}</td>
        </tr>
        <tr class="active">
            <td>City</td>
            <td>${address.city}</td>
        </tr>
        <tr class="active">
            <td>Post Index</td>
            <td>${address.postIndex}</td>
        </tr>
        <tr class="active">
            <td>Street</td>
            <td>${address.street}</td>
        </tr>
        <tr class="active">
            <td>HouseNumber</td>
            <td>${address.houseNumber}</td>
        </tr>
        <tr class="active">
            <td>Apartment</td>
            <td>${address.apartment}</td>
        </tr>
        </tbody>
    </table>

    <h4>Goods</h4>
    <table class="table table-striped table-hover ">
        <thead>
        <tr>
            <th>Good</th>
            <th>Quantity</th>
            <th>Cost</th>
        </tr>
        </thead>
        <tbody>
        <core:set var="total" value="${0}"/>
        <core:forEach var="entry" items="${goods}">
            <core:set var="total" value="${total + entry.key.price * entry.value}"/>
            <tr class="active">
                <td><core:out value="${entry.key.title}"/></td>
                <td><core:out value="${entry.value}"/></td>
                <td><core:out value="${entry.key.price * entry.value}"/></td>
            </tr>
        </core:forEach>
        <tr class="active">
            <td></td>
            <td></td>
            <td><core:out value="${total}"/></td>
        </tr>
        </tbody>
    </table>

</div>