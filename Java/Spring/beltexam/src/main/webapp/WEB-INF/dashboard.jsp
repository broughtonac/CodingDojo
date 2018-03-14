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
    <a href="/logout">logout</a>
    <fieldset>
        <legend>customers</legend>
        <table border="1">
            <tr>
                <th>name</th>
                <th>next due date</th>
                <th>amout due</th>
                <th>plan type</th>
            </tr>
            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td><c:out value="${customer.firstName} ${cusotmer.lastName}"/></td>
                    <td><c:out value="${customer.dueDate}"/></td>
                    <td><c:out value="${customer.plan.cost}"/></td>
                    <td><c:out value="${customer.plan.type}"/></td>
                </tr>
            </c:forEach>
        </table>
    </fieldset>
    <fieldset>
        <legend>plans</legend>
        <table border="1">
            <tr>
                <th>plan type</th>
                <th>payment cost</th>
                <th>availability</th>
                <th>subscribers</th>
                <th>actions</th>
            </tr>
            <c:forEach items="${plans}" var="plan">
                <tr>
                    <td><c:out value="${plan.type}"/></td>
                    <td><c:out value="${plan.cost}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${plan.isAvailable()}">available</c:when>
                            <c:otherwise>unavailable</c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${plan.users.size()}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${plan.isAvailable()}">
                                |<a href="/plans/deactivate/${plan.id}">deactivate</a>|
                            </c:when>
                            <c:otherwise>
                                |<a href="/plans/activate/${plan.id}">activate</a>|
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${plan.users.size() == 0}">
                                |<a href="/plans/delete/${plan.id}">delete</a>|
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </fieldset>
    <fieldset>
        <legend>new plan</legend>
        <form:form action="/plans/create" method="post" modelAttribute="plan">
            <form:label path="type">plan type
                <form:errors path="type"></form:errors>
                <form:input path="type"></form:input>
            </form:label><br>
            <form:label path="cost">payment cost
                <form:errors path="cost"></form:errors>
                <form:input path="cost"></form:input> 
            </form:label><br>
            <input type="submit" value="create">
        </form:form>
        <ul>
            <c:forEach items="${createPlanErrors}" var="createPlanError">
                <li><c:out value="${createPlanError.getDefaultMessage()}"/></li>
            </c:forEach>
        </ul>
    </fieldset>
</body>
</html>