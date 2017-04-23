<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
    <head>
        <title><tiles:insertAttribute name="title"/></title>
        <tiles:insertAttribute name="includes"/>

        <meta charset='UTF-8'/>
        <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/bootstrap.min.css'/>
        <script src='${pageContext.request.contextPath}/static/script/jquery-3.2.0.min.js'></script>
        <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/index.css'/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/<tiles:getAsString name="includecss"/>"/>
        <script>

            $(document).ready(function () {
                setTimeout(function () {
                    $('.loading').hide()
                    $('.loading').remove()
                }, 500)

                setInterval(function () {

                    $.ajax({
                        url: '${pageContext.request.contextPath}/renewcart',
                        data: {},
                        type: 'GET',
                        success: function (msg) {
                            $('.cart-size').html(msg)
                        }
                    })

                }, 1000)

            })
        </script>

    </head>
    <body>
        <div class='loading'></div>
        <header>
            <tiles:insertAttribute name="header"/>
        </header>

        <article class='main-contetn'>
            <tiles:insertAttribute name="content"/>
        </article>

        <footer>


        </footer>
    </body>
</html>