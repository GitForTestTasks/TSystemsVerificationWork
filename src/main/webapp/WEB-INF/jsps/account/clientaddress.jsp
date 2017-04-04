<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='col-lg-6 col-centered wraper-edit_address'>
    <core:if test="${success}">
        <h1 class="text-success">Success</h1>
    </core:if>
    <div class='well bs-component'>
        <form:form id="form-edit_address" name="form-edit_address" method="post"
                   action="${pageContext.request.contextPath}/account/clientaddress"
                   commandName="clientAddress" cssClass="form-horizontal">
            <fieldset>
                <legend>
                    Edit Address
                </legend>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Country
                    </label>
                    <div class='col-lg-10'>
                        <form:input placeholder="Country" path="country" required="true" type="text" name="country"
                                    cssClass="form-control input-sm"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="country"/>
                    </div>
                </div>


                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        City
                    </label>
                    <div class='col-lg-10'>
                        <form:input path="city" required="true" type="text" name="city" cssClass="form-control input-sm"
                                    placeholder='City'/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="city"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Post Index
                    </label>
                    <div class='col-lg-10'>
                        <form:input path="postIndex" required="true" type="text" name="postIndex"
                                    cssClass="form-control input-sm" placeholder='Post Index'/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="postIndex"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Street
                    </label>
                    <div class='col-lg-10'>
                        <form:input path="street" required="true" type="text" name="street"
                                    cssClass="form-control input-sm" placeholder='Street'/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="street"/>
                    </div>
                </div>


                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        House Number
                    </label>
                    <div class='col-lg-10'>
                        <form:input path="houseNumber" required="true" type="houseNumber"
                                    name="houseNumber" cssClass="form-control input-sm" placeholder='House Number'/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="houseNumber"/>
                    </div>
                </div>


                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Apartment
                    </label>
                    <div class='col-lg-10'>
                        <form:input path="apartment" required="true" type="apartment"
                                    name="apartment" cssClass="form-control input-sm" placeholder='Apartment'/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="apartment"/>
                    </div>
                </div>


                <div class='form-group'>
                    <div class='col-lg-10 col-lg-offset-2'>
                        <button type='submit' class='btn btn-primary btn-sm edit_address-btn-ok'>Submit</button>
                    </div>
                </div>
                <form:input type="hidden" name="clientAddressId" path="clientAddressId"/>
            </fieldset>
        </form:form>
    </div>

    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/address.css'/>
</div>