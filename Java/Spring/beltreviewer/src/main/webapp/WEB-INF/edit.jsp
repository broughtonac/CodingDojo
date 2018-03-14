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
        <legend>edit event</legend>
        <h1><c:out value="${event.name}"/></h1>
        <form:form action="/update/event/${event.id}" method="post" modelAttribute="newEvent">
        <form:label path="name">name
            <form:errors path="name"></form:errors>
            <form:input path="name"></form:input>
        </form:label><br>
        <form:label path="date">date
            <form:errors path="date"></form:errors>
            <form:input type="date" path="date"></form:input>
        </form:label><br>
        <form:label path="city">city
            <form:errors path="city"></form:errors>
            <form:input path="city"></form:input>
        </form:label><br>
        <form:label path="state">state
            <form:errors path="state"></form:errors>
            <form:select path="state">
                <%! String[] states = new String[] {"AL","AK","AS","AZ","AR","CA","CO","CT","DE","DC","FM","FL","GA","GU","HI","ID","IL","IN","IA","KS","KY","LA","ME","MH","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","MP","OH","OK","OR","PW","PA","PR","RI","SC","SD","TN","TX","UT","VT","VI","VA","WA","WV","WI","WY"}; %>
                <% for (String state : states) { %>
                    <form:option value="<%= state %>"><%= state %></form:option>
                <% } %>
            </form:select>
        </form:label><br>
        <input type="submit" value="edit">
    </form:form>
    </fieldset>
</body>
</html>