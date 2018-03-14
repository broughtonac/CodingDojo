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
    <h1>new person</h1>
    <fieldset>
    		<form:form method="POST" action="/createPerson" modelAttribute="person">
    			<p>
    				<form:label path="firstName">first name
    					<form:input path="firstName"/>
    				</form:label>
    			</p>
    			<p>
    				<form:label path="lastName">last name
    					<form:input path="lastName"/>
    				</form:label>
    			</p>
    			<input type="submit" value="add"/>
    		</form:form>
    </fieldset>
    <a href="/licenses/new">add license</a>
</body>
</html>