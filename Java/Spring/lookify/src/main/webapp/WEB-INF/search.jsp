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
		<legend>search by artist</legend>
    		<form action='/search' method='post'>
        		<input type='text' name='artist'>
        		<input type='submit' value='search'>
    		</form>
	</fieldset>
    <fieldset>
    		<legend>results</legend>
    		<table>
    			<tr>
    				<th>title</th>
    				<th>rating</th>
    				<th>actions</th>
                </tr>
                <c:forEach items="${results}" var="song" varStatus="loop">
                    <tr>
                        <td><a href="/songs/${song.id}"><c:out value="${song.title}"/></a></td>
                        <td><c:out value="${song.rating}"/></td>
                        <td>
                            <a href="/destroy/${song.id}">delete</a>
                        </td>
                    </tr>
                </c:forEach>
    		</table>
    </fieldset>
</body>
</html>