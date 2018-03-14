<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>edit</title>
</head>
<body>
	<p><a href="/languages">dashboard</a>
	<p><a href="/languages/delete/${language.id}">delete</a>
	<fieldset>
		<legend>edit</legend>
		<form:form method="POST" action="/languages/edit/${language.id}" modelAttribute="language">
        		<p>
        		<form:label path="name">name
        		<form:errors path="name"/>
        		<form:input path="name"/></form:label>
        		</p>
        		<p>
        		<form:label path="creator">creator
        		<form:errors path="creator"/>
        		<form:input path="creator"/></form:label>
        		</p>
        		<p>
        		<form:label path="version">version
        		<form:errors path="version"/>
        		<form:input path="version"/></form:label>
        		</p>
        		<input type="submit" value="enter data"/>
    </form:form>
	</fieldset>
</body>
</html>