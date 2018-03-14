<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
    <h1>new license</h1>
    <fieldset>
    		<form:form method="POST" action="/createLicense" modelAttribute="license">
    			<p>
    				<form:label path="person">person
                        <form:select path="person">
                            <c:forEach items="${persons}" var="person">
                                <form:option value="${person.id}" label="${person.firstName} ${person.lastName}"></form:option>
                            </c:forEach>
                        </form:select>
    				</form:label>
    			</p>
    			<p>
    				<form:label path="state">state
    					<form:input path="state"/>
    				</form:label>
    			</p>
    			<p>expiration date <input type="date" name="expiration_date"></p>
    			<input type="submit" value="create"/>
    		</form:form>
    </fieldset>
    <a href="/persons/new">add person</a>
</body>
</html>