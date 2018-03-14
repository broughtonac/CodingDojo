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
    <h1>new question</h1>
    <fieldset>
        <form:form method="POST" action="/create/question" modelAttribute="question">
            <form:label path="content">question
                <form:input path="content"/>
            </form:label><br>
            tags <input type="text" name="questionTags"><br>
            <input type="submit" value="create"/><br>
        </form:form>
    </fieldset>
    <a href="/questions">questions dashboard</a><br>
    <ul>
        <c:forEach items="${errors}" var="error">
            <li><c:out value="${error.getDefaultMessage()}"/></li>
        </c:forEach>
    </ul>
</body>
</html>