<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='wraper-home'>
    <h1>Products catalog:</h1>

    <div class='search-form-close search-form-close-hiden'></div>
    <div class='search-form-open-btn'>S e a r c h</div>
    <div class='search-form-wraper search-form-wraper-hiden'>

        <form method="get" action="${pageContext.request.contextPath}/goods" class='search-form form-horizontal'>
            <h4>Search form</h4>

            <input type='text' name='category' id='Category' placeholder='Category'
                   class='search-input form-control input-sm'/>

            <input type='text' name='title' id='Title' placeholder='Title' class='search-input form-control input-sm'/>

            <input type='text' name='price' id='Price' placeholder='Price' class='search-input form-control input-sm'/>

            <input name='minPrice' class='spinner search-form-price ' placeholder='Min Price' value='' min='1'
                   max='1000000' pattern='^[ 0-9]+$'/>

            <input name='maxPrice' class='spinner search-form-price' placeholder='Max Price' value='' min='1'
                   max='1000000' pattern='^[ 0-9]+$'/>

            <input type='text' name='brand' id='Brand' placeholder='Brand' class='search-input form-control input-sm'/>

            <input type='text' name='colour' id='Colour' placeholder='Colour'
                   class='search-input form-control input-sm'/>

            <label class='search-form-lable'>Order by:</label>
            <div class='search-form-selecnt-wraper'>
                <select name='orderby' class='form-control input-sm search-form-selecnt' id='Orderby'>
                    <option value="category">Category</option>
                    <option value="title">Title</option>
                    <option value="price">Price</option>
                    <option value="brand">Brand</option>
                    <option value="colour">Colour</option>
                </select>
            </div>

            <div class='search-form-btn-wraper'>
                <input type='submit' value='search' class='btn btn-default btn-sm search-form-btn search-form-btn-ok'/>
            </div>
        </form>
    </div>


    <div class='products'>

        <core:forEach var="row" items="${goods}">
            <div class='product'>
                <div class='product-wraper-img'>
                    <img class='product-img' src='${pageContext.request.contextPath}/static/images/${row.filePath}'/>
                </div>
                <div class='product-title'>
                    <core:out value="${row.title}"/>
                </div>
                <div class='product-description'>
                    <core:out value="${row.brand}"/>
                    <core:out value="${row.category}"/>
                    <core:out value="${row.colour}"/>
                    <core:out value="${row.count}"/>
                    <core:out value="${row.size}"/>
                    <core:out value="${row.weight}"/>
                </div>
                <div class='product-price'>
                    <core:out value="${row.price}"/>
                </div>
                <div class='product-quantity'>
                    <input class='spinner quantity-s' value='1' min='1' max='1000' pattern='^[ 0-9]+$'/>
                </div>
                <div class='product-btn'>
                    <div class='product-btn-add btn btn-primary btn-sm'
                            data='${row.goodId}'
                            url="${pageContext.request.contextPath}/buygood">
                        Add to Cart</div>
                    <div class='product-btn-detail btn btn-default btn-sm'>Product Detail</div>
                </div>
            </div>
        </core:forEach>

    </div>


    <ul class='pagination pagination-lg'>
        <core:forEach begin="1" end="${numberOfPages}" var="val">
            <li>
                <a href="${pageContext.request.contextPath}/goods?pageid=${val}">${val}</a>
            </li>
        </core:forEach>
    </ul>

    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/searchForm.css'/>
    <script src='${pageContext.request.contextPath}/static/script/searchForm.js'></script>

    <link href='${pageContext.request.contextPath}/static/css/jquery-ui-1.12.1.custom.min.css' rel='stylesheet'
          type='text/css'/>
    <script src='${pageContext.request.contextPath}/static/script/jquery-ui-1.12.1.custom.min.js'></script>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/static/css/orders.css'/>
    <script src='${pageContext.request.contextPath}/static/script/orders.js'></script>
</div>