<%-- 
    Document   : index
    Created on : Oct 4, 2014, 12:38:07 PM
    Author     : Blake
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Maintenance</title>
    </head>
    <body>
        <h1>Product Maintenance</h1>

        <!--<a href="ProductCatalog?action=viewProducts">View Products</a>-->

        <form action=<c:url value="ProductCatalog"/> method="post">
            <input type="hidden" name="action" value="viewProducts" />
            <input type="submit" value="View Products" />
        </form>
    </body>
</html>
