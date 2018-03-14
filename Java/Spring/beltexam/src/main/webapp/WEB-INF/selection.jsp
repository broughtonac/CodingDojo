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
    <h1>welcome, <c:out value="${thisUser.firstName}"/></h1>
    <fieldset>
        <legend>select plan</legend>
        <c:choose>
            <c:when test="${plans.isEmpty()}">
                <p>no plans are currently available</p>
            </c:when>
            <c:otherwise>
                <form action="/plans/bind" method="post" id="selectPlan">
                    <select name="dueDay" form="selectPlan">
                        <c:forEach items="${days}" var="day">
                            <option value="${day}"><c:out value="${day}"/> days</option> 
                        </c:forEach>
                    </select>
                    <select name="planId" form="selectPlan">
                        <c:forEach items="${plans}" var="plan">
                            <option value="${plan.id}"><c:out value="${plan.type}"/> ($<c:out value="${plan.cost}"/>)</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="sign up">
                </form>
            </c:otherwise>
        </c:choose>
    </fieldset>
</body>
</html>