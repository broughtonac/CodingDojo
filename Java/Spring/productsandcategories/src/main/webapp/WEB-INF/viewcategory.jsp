<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h1><c:out value="${category.name}"/></h1>
    <fieldset>
        <legend>products</legend>
        <c:forEach items="${categoryProducts}" var="productName">
            <p><c:out value="${productName}"/></p>
        </c:forEach>
    </fieldset>
    <form action="/bind/product/${category.id}" method="post" id="productForm">
        <select name="productId" form="productForm">
            <c:forEach items="${notCategoryProducts}" var="product">
                <option value="${product.id}"><c:out value="${product.name}"/></option>
            </c:forEach>
        </select>
        <input type="submit" value="bind product">
    </form>
</body>
</html>
