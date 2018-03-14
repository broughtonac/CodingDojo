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
    <h1>welcome <c:out value="${thisUser.firstName}"/></h1>
    <fieldset>
        <ul>
            <li>first name: <c:out value="${thisUser.firstName}"/></li>
            <li>last name: <c:out value="${thisUser.lastName}"/></li>
            <li>email: <c:out value="${thisUser.email}"/></li>
            <li>registration date: <c:out value="${thisUser.getCreatedAt()}"/></li>
            <li>last log in: <c:out value=""/></li>
        </ul>
    </fieldset>
</body>
</html>