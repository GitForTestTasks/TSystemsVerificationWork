<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<core:if test="${cartSize > 0}">


    <div class='cart-wraper'>
        <core:if test="${success}">
            <h1 class="text-success">Success</h1>
        </core:if>
        <sec:authorize access="!isAuthenticated()">
            <h1 class="text-warning">Please authorize to continue shopping</h1>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <div class='payment-methd'>
                <form:form class='form-horizontal' name="order" method="post"
                           action="${pageContext.request.contextPath}/cart"
                           commandName="order" cssClass="form-horizontal">

                    <fieldset>
                        <legend>
                            <h1>Confirm goods in your Cart</h1>
                        </legend>

                        <div class='form-group'>
                            <label class='col-lg-2 control-label'>
                                Payment Method
                            </label>
                            <div class='col-lg-10'>

                                <form:select cssClass='form-control payment-methd-select'
                                             path="paymentMethod" multiple="false">
                                    <form:options/>
                                </form:select>

                            </div>
                        </div>
                        <div class='form-group'>
                            <label class='col-lg-2 control-label'>
                            </label>
                            <div class='col-lg-10'>
                                <form:errors path="paymentMethod"/>
                            </div>
                        </div>


                        <div class='form-group'>
                            <label class='col-lg-2 control-label'>
                                Delivery Method
                            </label>
                            <div class='col-lg-10'>
                                <form:select path="deliveryMethod" multiple="false"
                                             cssClass='form-control payment-methd-select'>
                                    <form:options/>
                                </form:select>
                            </div>
                        </div>
                        <div class='form-group'>
                            <label class='col-lg-2 control-label'>
                            </label>
                            <div class='col-lg-10'>
                                <form:errors path="deliveryMethod"/>
                            </div>
                        </div>


                        <div class='form-group'>
                            <label class='col-lg-2 control-label'>
                                Client AddressId
                            </label>
                            <div class='col-lg-10'>
                                <form:select path="clientAddressId" multiple="false"
                                             class='form-control payment-methd-select'>
                                    <form:options items="${clientAddresses}" itemValue="clientAddressId"/>
                                </form:select>
                                <a class='cart-create-address-url hiden'
                                   href="${pageContext.request.contextPath}/account/clientaddress">
                                    Create new Addresses</a>
                            </div>
                        </div>
                        <div class='form-group'>
                            <label class='col-lg-2 control-label'>
                            </label>
                            <div class='col-lg-10'>
                                <form:errors path="clientAddressId"/>
                            </div>
                        </div>


                        <div class='form-group'>
                            <div class='col-lg-10 col-lg-offset-2'>
                                <button type='submit' class='btn btn-primary'>Create Order</button>
                            </div>
                        </div>

                    </fieldset>
                </form:form>
            </div>
        </sec:authorize>

        <div class='products cart-products'>

            <core:forEach var="entry" items="${goods}">
                <div class='product'>
                    <div class='product-wraper-img'>
                        <img class='product-img'
                             src='${pageContext.request.contextPath}/static/images/${entry.key.filePath}'/>
                    </div>
                    <div class='product-title'>
                        <core:out value="${entry.key.title}"/>
                    </div>
                    <div class='product-description'>
                        <core:out value="${entry.key.brand}"/>
                        <core:out value="${entry.key.category}"/>
                        <core:out value="${entry.key.colour}"/>
                        <core:out value="${entry.key.count}"/>
                        <core:out value="${entry.key.size}"/>
                        <core:out value="${entry.key.weight}"/>
                    </div>
                    <div class='product-price'>
                        <core:out value="${entry.key.price}"/>
                    </div>
                    <div class='product-quantity'>
                        <div>
                            Qantity: <strong>${entry.value}</strong>
                        </div>
                    </div>

                    <div class='product-btn'>
                        <a class='product-btn-del btn btn-default btn-xs'
                           href="${pageContext.request.contextPath}/cart?deleteItemFromCart=${entry.key.goodId}">
                            Delete from cart</a>
                    </div>

                </div>
            </core:forEach>


            <core:set var="total" value="${0}"/>
            <core:forEach var="article" items="${goods}">
                <core:set var="total" value="${total + article.key.price * article.value}"/>
            </core:forEach>
        </div>

        <h1 class="text-info">Total cost : <core:out value="${total}"/></h1>

        <sec:authorize access="!isAuthenticated()">
            <a href='${pageContext.request.contextPath}/login'
               class='btn btn-primary btn-lg'>
                Continue
            </a>
        </sec:authorize>


        <link href='${pageContext.request.contextPath}/static/css/jquery-ui-1.12.1.custom.min.css' rel='stylesheet'
              type='text/css'/>
        <script src='${pageContext.request.contextPath}/static/script/jquery-ui-1.12.1.custom.min.js'></script>
        <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/goods.css'/>
            <%--<script src='${pageContext.request.contextPath}/static/script/orders.js'></script>--%>

        <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/cart.css'/>
        <script src='${pageContext.request.contextPath}/static/script/cart.js'></script>

    </div>
</core:if>