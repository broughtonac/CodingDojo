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
    <h1><c:out value="${product.name}"/></h1>
    <fieldset>
        <legend>categories</legend>
        <c:forEach items="${productCategories}" var="categoryName">
            <p><c:out value="${categoryName}"/></p>
        </c:forEach>
    </fieldset>
    <form action="/bind/category/${product.id}" method="post" id="categoryForm">
        <select name="categoryId" form="categoryForm">
            <c:forEach items="${notProductCategories}" var="category">
                <option value="${category.id}"><c:out value="${category.name}"/></option>
            </c:forEach>
        </select>
        <input type="submit" value="bind category">
    </form>
</body>
</html>
