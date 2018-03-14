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
    <a href="/logout">logout</a>
    <h2><c:out value="${thisUser.firstName}"/></h2>
    <ul>
        <li>plan type: <c:out value="${thisUser.plan.type}"/></li>
        <li>next payment due date: <c:out value="${thisUser.dueDate}"/></li>
        <li>amoutn due: <c:out value="${thisUser.plan.cost}"/></li>
        <li>user since: <c:out value="${thisUser.createdAt}"/></li>
    </ul>
</body>
</html>