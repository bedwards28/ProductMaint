<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : CatalogDisplay
    Created on : Sep 6, 2014, 9:00:36 PM
    Author     : rfoy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
    </head>
    <body>
        <h1>Products in Catalog</h1>
        <ol>
            <c:forEach var="products" items="${products}">
                <li>${products}</li>
            </c:forEach>
        </ol>
        
        <br>
    </body>
</html>
