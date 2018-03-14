<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <fieldset>
        <legend>events in your state</legend>
        <table border="1">
            <tr>
                <th>name</th>
                <th>date</th>
                <th>city</th>
                <th>host</th>
                <th>actions</th>
            </tr>
            <c:forEach items="${eventsAttendingInState}" var="event">
                <tr>
                    <td><a href="/events/${event.id}"><c:out value="${event.name}"/></a></td>
                    <td><c:out value="${event.date}"/></td>
                    <td><c:out value="${event.city}"/></td>
                    <td><c:out value="${event.host.firstName} ${event.host.lastName}"/></td>
                    <c:choose>
                        <c:when test="${event.host.id == thisUser.id}">
                            <td>
                                <a href="/events/${event.id}/edit">edit</a>
                                <a href="/events/${event.id}/delete">delete</a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>attending | <a href="/unattend/${event.id}">unattend</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            <c:forEach items="${eventsNotAttendingInState}" var="event">
                <tr>
                    <td><a href="/events/${event.id}"><c:out value="${event.name}"/></a></td>
                    <td><c:out value="${event.date}"/></td>
                    <td><c:out value="${event.city}"/></td>
                    <td><c:out value="${event.host.firstName} ${event.host.lastName}"/></td>
                    <td><a href="/attend/${event.id}">attend</a></td>
                </tr>
            </c:forEach>
        </table>
    </fieldset>
    <fieldset>
        <legend>events in other states</legend>
        <table border="1">
            <tr>
                <th>name</th>
                <th>date</th>
                <th>city</th>
                <th>state</th>
                <th>host</th>
                <th>action</th>
            </tr>
            <c:forEach items="${eventsAttendingNotInState}" var="event">
                <tr>
                    <td><a href="/events/${event.id}"><c:out value="${event.name}"/></a></td>
                    <td><c:out value="${event.date}"/></td>
                    <td><c:out value="${event.city}"/></td>
                    <td><c:out value="${event.state}"/></td>
                    <td><c:out value="${event.host.firstName} ${event.host.lastName}"/></td>
                    <c:choose>
                        <c:when test="${event.host.id == thisUser.id}">
                            <td>
                                <a href="/events/${event.id}/edit">edit</a>
                                <a href="/events/${event.id}/delete">delete</a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>attending | <a href="/unattend/${event.id}">unattend</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            <c:forEach items="${eventsNotAttendingNotInState}" var="event">
                <tr>
                    <td><a href="/events/${event.id}"><c:out value="${event.name}"/></a></td>
                    <td><c:out value="${event.date}"/></td>
                    <td><c:out value="${event.city}"/></td>
                    <td><c:out value="${event.state}"/></td>
                    <td><c:out value="${event.host.firstName} ${event.host.lastName}"/></td>
                    <td><a href="/attend/${event.id}">attend</a></td>
                </tr>
            </c:forEach>
        </table>
    </fieldset>
    <fieldset>
        <legend>new event</legend>
        <form:form action="/create/event" method="post" modelAttribute="event">
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
            <input type="submit" value="create">
        </form:form>
    </fieldset>
    <a href="/logout">logout</a>
</body>
</html>
