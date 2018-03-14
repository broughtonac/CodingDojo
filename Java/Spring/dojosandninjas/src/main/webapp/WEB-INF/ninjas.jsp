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
    <h1>new ninja</h1>
    <fieldset>
    		<form:form method="POST" action="/createNinja" modelAttribute="ninja">
    			<p>
    				<form:label path="dojo">dojo
                        <form:select path="dojo">
                            <c:forEach items="${dojos}" var="dojo">
                                <form:option value="${dojo.id}" label="${dojo.name}"></form:option>
                            </c:forEach>
                        </form:select>
    				</form:label>
    			</p>
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
    			<p>
    				<form:label path="age">age
    					<form:input path="age"/>
    				</form:label>
    			</p>
    			<input type="submit" value="create"/>
    		</form:form>
    </fieldset>
    <a href="/dojos/new">add dojo</a>
</body>
</html>