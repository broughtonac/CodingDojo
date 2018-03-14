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
    <fieldset>
        <legend>event</legend>
        <h1><c:out value="${event.name}"/></h1>
        host: <c:out value="${event.host.firstName} ${event.host.lastName}"/><br>
        date: <c:out value="${event.date}"/><br>
        location: <c:out value="${event.city}, ${event.state}"/><br>
        number of people going: <c:out value="${event.getUsers().size() + 1}"/>
    </fieldset>
    <fieldset>
        <legend>attendees</legend>
        <table border="1">
            <tr>
                <th>name</th>
                <th>city</th>
            </tr>
            <tr>
                <td><c:out value="${event.host.firstName} ${event.host.lastName}"/></td>
                <td><c:out value="${event.host.city}, ${event.host.state}"/></td>
            </tr>
            <c:forEach items="${event.getUsers()}" var="attendee">
                <td><c:out value="${attendee.firstName} ${attendee.lastName}"/></td>
                <td><c:out value="${attendee.city}, ${attendee.state}"/></td>
            </c:forEach>
        </table>
    </fieldset>
    <fieldset>
        <legend>messages</legend>
        <c:forEach items="${event.getMessages()}" var="message">
            <p>
                <c:out value="${message.user.firstName} ${message.user.lastName}"></c:out>:
                <c:out value="${message.content}"></c:out>
            </p>
        </c:forEach>
    </fieldset>
    <fieldset>
        <legend>new message</legend>
        <form:form action="/create/message/${event.id}" method="post" modelAttribute="message">
            <form:label path="content">message
                <form:errors path="content"></form:errors>
                <form:input path="content"></form:input>
            </form:label><br>
            <input type="submit" value="post">
        </form:form>
    </fieldset>
</body>
</html>