<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<html>
<head>
    <title><tiles:insertAttribute name="title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    <link rel="stylesheet" href="https://bootswatch.com/yeti/bootstrap.min.css">

    <tiles:insertAttribute name="includes"/>
</head>
<body>
<div class="header">
    <tiles:insertAttribute name="header"/>
</div>
<div class="content">
    <tiles:insertAttribute name="content"/>
</div>


    <hr/>
    <div class="footer">
        <tiles:insertAttribute name="footer"/>
    </div>
</body>
</html>
