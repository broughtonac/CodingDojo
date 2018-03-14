<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>languages</title>
</head>
<body>
	<fieldset>
		<legend>languages</legend>
		<table>
            <tr>
                <th>name</th>
                <th>creator</th>
                <th>version</th>
                <th>actions</th>
            </tr>
            <c:forEach items="${languages}" var="language" varStatus="loop">
                <tr>
                    <td><a href="/languages/${language.id}"><c:out value="${language.name}"/></a></td>
                    <td><c:out value="${language.creator}"/></td>
                    <td><c:out value="${language.version}"/></td>
                    <td>
                        <a href="/languages/edit/${language.id}">edit</a>
                        <a href="/languages/delete/${language.id}">delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
	</fieldset>
	<fieldset>
		<legend>create</legend>
    		<form:form method="POST" action="/create" modelAttribute="language">
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