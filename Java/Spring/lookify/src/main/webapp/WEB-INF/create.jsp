<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>add song</title>
</head>
<body>
	<p><a href="/dashboard">dashboard</a></p>
    <fieldset>
		<legend>create</legend>
        <form:form method="POST" action="/create" modelAttribute="song">
            <p>
                <form:label path="title">title
                    <form:errors path="title"/>
                    <form:input path="title"/>
                </form:label>
            </p>
            <p>
                <form:label path="artist">artist
                    <form:errors path="artist"/>
                    <form:input path="artist"/>
                </form:label>
            </p>
            <p>
                <form:label path="rating">rating
                <form:errors path="rating"/>
                <form:select path="rating">
                    <form:option value="1"/>
                    <form:option value="2"/>
                    <form:option value="3"/>
                    <form:option value="4"/>
                    <form:option value="5"/>
                    <form:option value="6"/>
                    <form:option value="7"/>
                    <form:option value="8"/>
                    <form:option value="9"/>
                    <form:option value="10"/>
                </form:select>
                </form:label>
            </p>
            <input type="submit" value="enter data"/>
        </form:form>
	</fieldset>
</body>
</html>