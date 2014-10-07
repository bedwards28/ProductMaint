<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : CatalogDisplay
    Created on : Sep 6, 2014, 9:00:36 PM
    Author     : rfoy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="products.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
    </head>
    <body>
        <h1>Products</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Release Date</th>
                    <th>Years Released</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.code}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>${product.releaseDate}</td>
                        <td>${product.yearsReleased}</td>
                        <td>edit</td>
                        <td>delete</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <form action="AddProduct.jsp" method="post">
            <input type="submit" value="Add Product" name="addProductButton" action="addProduct"/>
        </form>
    </body>
</html>
