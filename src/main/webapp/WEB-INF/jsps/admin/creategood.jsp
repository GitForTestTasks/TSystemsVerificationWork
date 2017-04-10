<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='col-lg-6 col-centered add_product-wraper'>
    <div class='well bs-component'>
        <form:form name="creategood" method="post" enctype="multipart/form-data"
                   action="${pageContext.request.contextPath}/admin/creategood"
                   commandName="good" cssClass="form-horizontal" id='form-add_product'>
            <fieldset>
                <form:input path="goodId" type="hidden" name="title"/>
                <legend>
                    Product
                </legend>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Title
                    </label>
                    <div class='col-lg-10'>
                        <form:input cssClass="form-control input-sm"
                                    placeholder='Title'
                                    path="title" required="true" type="text" name="title"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="title" cssClass="text-danger col-lg-10"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Price
                    </label>
                    <div class='col-lg-10'>
                        <form:input cssClass="form-control input-sm" path="price" required="true"
                                    pattern="\d+(\.\d{4})?" type="text"
                                    placeholder='Price'
                                    name="price"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="price" cssClass="text-danger col-lg-10"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-lg-2 control-label">Category</label>
                    <div class="col-lg-10">
                        <form:input cssClass="form-control input-sm" required="true" path="category" type="text"
                                    placeholder='Category'
                                    name="category"/>
                        <!--
                        <select class="form-control" id="select" name="Category">
                            <option value='{{c.CategoryId}}'>{{c.Name}}</option>
                        </select>
                        -->
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="category" cssClass="text-danger col-lg-10"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Brand
                    </label>
                    <div class='col-lg-10'>
                        <form:input cssClass="form-control input-sm" path="brand" type="text" name="brand"
                                    placeholder='Brand'/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="brand" cssClass="text-danger col-lg-10"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Colour
                    </label>
                    <div class='col-lg-10'>
                        <form:input cssClass="form-control input-sm" path="colour" type="text" name="colour"
                                    placeholder='Colour'/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="colour" cssClass="text-danger col-lg-10"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Weight
                    </label>
                    <div class='col-lg-10'>
                        <form:input cssClass="form-control input-sm" path="weight" pattern="\d+(\.\d{3})?" type="text"
                                    placeholder='Weight'
                                    name="weight"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="weight" cssClass="text-danger col-lg-10"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Size
                    </label>
                    <div class='col-lg-10'>
                        <form:input cssClass="form-control input-sm" path="size" type="text" name="size"
                                    placeholder='Size'/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="size" cssClass="text-danger col-lg-10"/>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Count
                    </label>
                    <div class='col-lg-10'>
                        <form:input cssClass="form-control input-sm" path="count" required="true" pattern="^[ 0-9]+$"
                                    type="text"
                                    placeholder='Count'
                                    name="count"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="count" cssClass="text-danger col-lg-10"/>
                    </div>
                </div>


                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Select img
                    </label>
                    <div class='col-lg-10'>
                        <input type="file" name="file" class="btn-file btn btn-default btn-sm"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="filePath" cssClass="text-danger col-lg-10"/>
                    </div>
                </div>


                <div class='form-group'>
                    <div class='col-lg-10 col-lg-offset-2'>
                        <button type='submit' class='btn btn-primary btn-sm add_product-btn-ok'>Submit</button>
                    </div>
                </div>

            </fieldset>

        </form:form>

    </div>
    <%--<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/creategood.css'/>--%>
</div>