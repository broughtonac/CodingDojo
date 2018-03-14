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
    <h1><c:out value="${person.firstName} ${person.lastName}"/></h1>
    <p>license number: <c:out value="${license.number}"/></p>
    <p>state: <c:out value="${license.state}"/></p>
    <p>expiration date: <c:out value="${license.expiration_date}"/></p>
</body>
</html>