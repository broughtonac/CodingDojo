<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>view</title>
</head>
<body>
	<fieldset>
		<legend>view</legend>
		<p>name: "${language.name}"</p>
		<p>creator: "${language.creator}"</p>
		<p>version: "${language.version}"</p>
		<p><a href="/languages/edit/${language.id}">edit</a></p>
		<p><a href="/languages/delete/${language.id}">delete</a></p>
	</fieldset>
</body>
</html>