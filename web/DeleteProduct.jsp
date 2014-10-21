<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : DeleteProduct
    Created on : Oct 19, 2014, 6:50:41 PM
    Author     : Blake
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="products.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Product</title>
    </head>
    <body>
        <h1>Are you sure you want to delete this product?</h1>
        <label>Code:</label> <p>${product.code}</p> <br>
        <label>Description:</label> <p>${product.description}</p> <br>
        <label>Price:</label> <p>${product.price}</p> <br>
        <label>Release Date:</label> <p>${product.releaseDate}</p> <br>
        <label>&nbsp;</label>
        <form action="<c:url value="ProductCatalog"/>" method="post">
            <input type="submit" value="Yes" name="deleteProductButton">
            <input type="submit" value="No" name="viewProductsButton">
            <input type="hidden" name="code" value="${product.code}">
            <input type="hidden" name="description" value="${product.description}">
            <input type="hidden" name="price" value="${product.price}">
            <input type="hidden" name="releaseDate" value="${product.releaseDate}">
        </form>
    </body>
</html>
