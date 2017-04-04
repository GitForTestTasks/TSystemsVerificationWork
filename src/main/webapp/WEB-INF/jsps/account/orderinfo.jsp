<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<core:out value="${order}"/>
<br/>

Address : <core:out value="${address}"/>
<br/>


<core:forEach var="entry" items="${goods}">
    </br>Good : <core:out value="${entry.key}"/>
    </br>Quantity : <core:out value="${entry.value}"/>
    </br>Cost : <core:out value="${entry.key.price * entry.value}"/>
</core:forEach>


<core:set var="total" value="${0}"/>
<core:forEach var="article" items="${goods}">
    <core:set var="total" value="${total + article.key.price * article.value}"/>
</core:forEach>
<br/>
Total cost : <core:out value="${total}"/>