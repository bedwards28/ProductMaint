<%-- 
    Document   : AddProduct
    Created on : Oct 4, 2014, 3:57:16 PM
    Author     : Blake
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>
        <h1>Add Product</h1>
        <form name="addProduct" action="AddProduct" method="POST">
            <label>Code:</label>
            <input type="text" name="code" value="" size="20" /><br>
            <label>Description:</label>
            <input type="text" name="description" value="" size="20" /><br>
            <label>Price:</label>
            <input type="number" name="price" value="" size="20" /><br>
            <input type="submit" value="Add Product" name="addProductButton" />
            <input type="submit" value="View Products" name="viewProductsButton" />
        </form>
    </body>
</html>
