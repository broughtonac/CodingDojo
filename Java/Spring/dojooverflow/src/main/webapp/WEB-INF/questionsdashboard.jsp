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
    <h1>questions dashboard</h1>
    <c:forEach items="${questions}" var="question">
        <p>
            <a href="/questions/${question.id}/">
                <c:out value="${question.content}"/>
            </a>
            <c:forEach items="${question.tags}" var="tag">
                <c:out value="${tag.subject}"></c:out>
            </c:forEach>
        </p>
    </c:forEach>
    <a href="/questions/new">new question</a>
</body>
</html>