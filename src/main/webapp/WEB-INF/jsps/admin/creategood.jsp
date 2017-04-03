<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create Good</title>
</head>
<body>

<div class="forms well bs-component">

    <form:form name="creategood" method="post" action="${pageContext.request.contextPath}/admin/creategood"
               commandName="good" cssClass="form-horizontal">
        <fieldset>
            <legend>Create good</legend>
            <div class="form-group">
                <label for="inputTitle" class="col-lg-2 control-label">Title</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="title" required="true" type="text" name="title"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="title" cssClass="text-danger col-lg-10"/>
            </div>

            <div class="form-group">
                <label for="inputPrice" class="col-lg-2 control-label">Price</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="price" required="true" pattern="\d+(\.\d{4})?" type="text"
                                name="price"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="price" cssClass="text-danger col-lg-10"/>
            </div>

            <div class="form-group">
                <label for="inputCategory" class="col-lg-2 control-label">Category</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" required="true" path="category" type="text" name="category"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="category" cssClass="text-danger col-lg-10"/>
            </div>

            <div class="form-group">
                <label for="inputWeight" class="col-lg-2 control-label">Weight</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="weight" pattern="\d+(\.\d{3})?" type="text"
                                name="weight"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="weight" cssClass="text-danger col-lg-10"/>
            </div>

            <div class="form-group">
                <label for="inputSize" class="col-lg-2 control-label">Size</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="size" type="text" name="size"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="size" cssClass="text-danger col-lg-10"/>
            </div>

            <div class="form-group">
                <label for="inputСount" class="col-lg-2 control-label">Count</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="count" required="true" pattern="^[ 0-9]+$" type="text"
                                name="count"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="count" cssClass="text-danger col-lg-10"/>
            </div>

            <div class="form-group">
                <label for="inputBrand" class="col-lg-2 control-label">Brand</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="brand" type="text" name="brand"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="brand" cssClass="text-danger col-lg-10"/>
            </div>

            <div class="form-group">
                <label for="inputColour" class="col-lg-2 control-label">Colour</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="colour" type="text" name="colour"/>
                </div>
            </div>
            <div class="error form-group">
                <form:errors path="colour" cssClass="text-danger col-lg-10"/>
            </div>

            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <input class="button btn btn-primary" type="submit" name="submit" value="Create Good">
                </div>
            </div>
        </fieldset>
    </form:form>

</div>


</body>
</html>
