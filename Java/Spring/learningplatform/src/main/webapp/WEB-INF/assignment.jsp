<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>fortran dashboard</title>
</head>
<body>
    <h1>fortran</h1>
    <p><a href="/m/38/0553/0733">set up</a></p>
    <p><a href="/m/38/0483/0345">forms</a></p>
    <p><a href="/m/38/0553/0342">cards</a></p>
    <p><a href="/m/26/0553/0348">advanced</a></p>
    <p><a href="/m/26/0483/2342">binary</a></p>
    <form>
        <input type="checkbox"> assignment completed
    </form>
    <fieldset>
        <p><c:out value="${placeholder}"></c:out></p>
    </fieldset>
</body>
</html>