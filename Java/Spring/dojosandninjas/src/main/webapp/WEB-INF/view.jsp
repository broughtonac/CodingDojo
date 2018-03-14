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
    <h1><c:out value="${dojo.name}"/> location</h1>
    	<fieldset>
		<legend>ninjas</legend>
		<table>
            <tr>
                <th>first name</th>
                <th>last name</th>
                <th>age</th>
            </tr>
            <c:forEach items="${ninjas}" var="ninja" varStatus="loop">
                <tr>
                    <td><c:out value="${ninja.firstName}"/></td>
                    <td><c:out value="${ninja.lastName}"/></td>
                    <td><c:out value="${ninja.age}"/></td>
                </tr>
            </c:forEach>
        </table>
	</fieldset>
</body>
</html>