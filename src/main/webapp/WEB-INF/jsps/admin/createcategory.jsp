<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>


<div class='col-lg-6 col-centered wraper-edit_categories'>
    <div class='well bs-component'>
        <form:form method="post"
                   action="${pageContext.request.contextPath}/admin/createcategory"
                   class='form-horizontal'
                   name='form-edit_categories'
                   id='form-edit_categories'
                   commandName="category">

            <form:input path="categoryId" type="hidden" name="categoryId"/>
            <fieldset>
                <legend>
                    Edit Categories
                </legend>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>

                    </label>
                    <div class='col-lg-10'>
                        <core:if test="${fail}">
                            <h1>That category already exists</h1>
                        </core:if>
                    </div>
                </div>

                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                        Name
                    </label>
                    <div class='col-lg-10'>
                        <form:input placeholder='Name' cssClass="form-control input-sm" path="name" required="true"
                                    type="text" name="name"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label class='col-lg-2 control-label'>
                    </label>
                    <div class='col-lg-10'>
                        <form:errors path="name"/>
                    </div>
                </div>

                <div class='form-group'>
                    <div class='col-lg-10 col-lg-offset-2'>
                        <button type='submit' class='btn btn-primary btn-sm edit_categories-btn-ok'>Submit</button>
                    </div>
                </div>

            </fieldset>
        </form:form>
    </div>

</div>