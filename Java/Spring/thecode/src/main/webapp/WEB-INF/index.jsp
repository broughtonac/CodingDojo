<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ page import="org.springframework.web.servlet.mvc.support.RedirectAttributes" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>try</title>
</head>
<body>
	<p>${myErrors}</p>
    <h3>enter passcode</h3>
    <form action="/process" method="post">
        <input type='text' name='passcode'>
        <input type='submit' value='try passcode'>
    </form>
    <p>hint: bushido</p>
</body>
</html>