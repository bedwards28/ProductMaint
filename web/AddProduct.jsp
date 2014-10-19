<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
    Document   : AddProduct
    Created on : Oct 4, 2014, 3:57:16 PM
    Author     : Blake
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="products.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${editProduct eq 'editProduct'}">
                <h1>Edit Product</h1>
            </c:when>
            <c:otherwise>
                <h1>Add Product</h1>
            </c:otherwise>
        </c:choose>

        <form name="addProduct" action="ProductCatalog" method="POST">
            <label>Code:</label>
            <input type="text" name="code" value="${code}" 
                   <c:if test="${editProduct eq 'editProduct'}">
                       readonly
                   </c:if> ><br>
            <label>Description:</label>
            <input type="text" name="description" value="${description}"><br>
            <label>Price:</label>
            <input type="text" name="price" 
                   value="<fmt:formatNumber value="${price}" 
                                     maxFractionDigits="2"
                                     minFractionDigits="2"/>"><br>
            <label>Release Date:</label>
            <input type="date" name="releaseDate" value="${releaseDate}"><br>
            <label>&nbsp;</label>
            <c:choose>
                <c:when test="${editProduct eq 'editProduct'}">
                    <input type="submit" value="Edit Product" name="editProductButton">
                </c:when>
                <c:otherwise>
                    <input type="submit" value="Add Product" name="addProductButton">
                </c:otherwise>
            </c:choose>
            <input type="submit" value="View Products" name="viewProductsButton">
        </form>
        <h2>${errmsg}</h2>
    </body>
</html>
