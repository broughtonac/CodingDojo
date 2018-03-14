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
    <form method="post" action="/login">
        email <input type="text" name="email"><br>
        password <input type="password" name="password"><br>
        <input type="submit" value="login">
    </form>
    <ul>
        <c:forEach items="${loginErrors}" var="loginError">
            <li><c:out value="${loginError}"/></li>
        </c:forEach>
    </ul>
    <form:form action="/register" method="post" modelAttribute="user">
        <p>
        <form:label path="firstName">first name
            <form:errors path="firstName"></form:errors>
            <form:input path="firstName"></form:input>
        </form:label>
        </p>
        <p>
        <form:label path="lastName">last name
            <form:errors path="lastName"></form:errors>
            <form:input path="lastName"></form:input>
        </form:label>
        </p>
        <p>
        <form:label path="email">email
            <form:errors path="email"></form:errors>
            <form:input path="email"></form:input>
        </form:label>
        </p>
        <p>
        <form:label path="password">password
            <form:errors path="password"></form:errors>
            <form:input type="password" path="password"></form:input>
        </form:label>
        </p>
        <p>
        <form:label path="confirm">confirm
            <form:errors path="confirm"></form:errors>
            <form:input type="password" path="confirm"></form:input>
        </form:label>
        </p>
        <input type="submit" value="register">
    </form:form>
    <ul>
        <c:forEach items="${registrationErrors}" var="registrationError">
            <li><c:out value="${registrationError.getDefaultMessage()}"/></li>
        </c:forEach>
    </ul>
</body>
</html>