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
    <h1>new category</h1>
    <fieldset>
    		<form:form method="POST" action="/create/category" modelAttribute="category">
    			<p>
    				<form:label path="name">name
    					<form:input path="name"/>
    				</form:label>
    			</p>
    			<input type="submit" value="create"/>
    		</form:form>
    </fieldset>
    <a href="/products/new">new product</a>
</body>
</html>