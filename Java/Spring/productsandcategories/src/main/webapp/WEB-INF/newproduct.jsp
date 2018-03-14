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
    <h1>new product</h1>
    <fieldset>
    		<form:form method="POST" action="/create/product" modelAttribute="product">
    			<p>
    				<form:label path="name">name
    					<form:input path="name"/>
    				</form:label>
                </p>
                <p>
    				<form:label path="description">description
    					<form:input path="description"/>
    				</form:label>
                </p>
                <p>
    				<form:label path="price">price
    					<form:input path="price"/>
    				</form:label>
    			</p>
    			<input type="submit" value="create"/>
    		</form:form>
    </fieldset>
    <a href="/categories/new">new category</a>
</body>
</html>