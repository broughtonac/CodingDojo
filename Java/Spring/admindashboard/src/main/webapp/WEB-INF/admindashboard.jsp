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
    <table style="border: 1px solid black">
        <tr>
            <th>name</th>
            <th>email</th>
            <th>actions</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.firstName} ${user.lastName}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${user.isAdmin() == false}">
                            <a href="/destroy/${user.id}">delete</a> <a href="/admin/${user.id}">make admin</a>
                        </c:when>
                        <c:otherwise>
                            admin
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>