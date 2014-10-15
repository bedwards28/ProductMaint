<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <td><fmt:formatNumber value="${product.price}" type="currency"/></td>
                        <td>${product.releaseDate}</td>
                        <td>${product.yearsReleased}</td>
                        <td>
                            <form action="<c:url value="ProductCatalog"/>" method="post">
                                <input type="hidden" name="action" value="editProduct">
                                <input type="hidden" name="code" value="${product.code}">
                                <input type="hidden" name="description" value="${product.description}">
                                <input type="hidden" name="price" value="${product.price}">
                                <input type="submit" value="Edit" name="editProductButton">
                            </form>
                        </td>
                        <td>
                            <form action="<c:url value="/AddProduct.jsp"/>" method="post">
                                <input type="hidden" name="action" value="deleteProduct">
                                <input type="submit" value="Delete" name="deleteProductButton">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        
        <form action="<c:url value="ProductCatalog" />" method="post">
            <input type="hidden" name="action" value="addProduct">
            <input type="submit" value="Add Product">
        </form>
</body>
</html>
