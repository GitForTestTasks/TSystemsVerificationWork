<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='wraper-addresses'>
    <div class='wraper-table-addresses'>
        <h1>Your Addresses</h1>
        <table class='table table-striped table-hover table-addresses'>
            <thead class='header-addresses'>
            <tr>
                <th>#</th>
                <th>Country</th>
                <th>City</th>
                <th>PostIndex</th>
                <th>Street</th>
                <th>HouseNumber</th>
                <th>Apartment</th>
                <th></th>
            </tr>
            </thead>
            <tbody class='addresses'>


            <core:set var="iterator" value="${0}"/>
            <core:forEach var="entry" items="${addresses}">
                <core:set var="iterator" value="${iterator + 1}"/>

                <tr class='address ${iterator % 2 ==0 ? "active": "info"}'>
                    <td>${iterator}</td>
                    <td class='address-Country'>${entry.country}</td>
                    <td class='address-City'>${entry.city}</td>
                    <td class='address-PostIndex'>${entry.postIndex}</td>
                    <td class='address-Street'>${entry.street}</td>
                    <td class='address-HouseNumber'>${entry.houseNumber}</td>
                    <td class='address-Apartment'>${entry.apartment}</td>

                    <td class='address-btn-wraper' data='${entry.clientAddressId}'>
                        <a href='${pageContext.request.contextPath}/account/clientaddress?clientAddressId=${entry.clientAddressId}'
                           class='btn btn-primary btn-xs address-btn address-btn-edt'>
                            Edit
                        </a>
                    </td>
                </tr>
            </core:forEach>

            </tbody>
        </table>

        <a class='btn btn-primary btn-xs address-btn address-btn-create'
           href="${pageContext.request.contextPath}/account/clientaddress">Create new Addresses</a>

        <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/addresses.css'/>
    </div>
</div>