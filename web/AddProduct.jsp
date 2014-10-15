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
        <h1>Add Product</h1>
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
            <label>&nbsp;</label>
            <input type="submit" value="Add Product" name="addProductButton">
            <input type="submit" value="View Products" name="viewProductsButton">
        </form>
        <h2>${errmsg}</h2>
    </body>
</html>
