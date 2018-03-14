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
    <h1><c:out value="${question.content}"/></h1>
    <fieldset>
        <legend>tags</legend>
        <c:forEach items="${question.tags}" var="tag">
            <p><c:out value="${tag.subject}"/></p>
        </c:forEach>
    </fieldset>
    <fieldset>
        <legend>answers</legend>
        <c:forEach items="${question.answers}" var="answer">
            <p><c:out value="${answer.content}"/></p>
        </c:forEach>
    </fieldset>
    <fieldset>
        <legend>new answer</legend>
        <form:form action="/create/answer/${question.id}" method="post" modelAttribute="answer">
            <form:label path="content">answer
                <form:input path="content"></form:input>
            </form:label>
            <input type="submit" value="create">
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