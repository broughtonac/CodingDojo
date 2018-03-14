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
	<p><a href="/dashboard">dashboard</a></p>
    <fieldset>
    		<legend>top ten</legend>
    		<table>
    			<tr>
    				<th></th>
    				<th></th>
    				<th></th>
                </tr>
                <c:forEach items="${topTen}" var="song" varStatus="loop">
                    <tr>
                        <td><c:out value="${song.rating}"/></td>
                        <td> - <a href="/songs/${song.id}"><c:out value="${song.title}"/></a></td>
                        <td> - <c:out value="${song.artist}"/></td>
                    </tr>
                </c:forEach>
    		</table>
    </fieldset>
</body>
</html>