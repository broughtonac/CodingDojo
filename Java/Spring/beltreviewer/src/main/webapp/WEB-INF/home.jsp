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
        <legend>login</legend>
        <form method="post" action="/login">
            email <input type="text" name="email"><br>
            password <input type="password" name="password"><br>
            <input type="submit" value="login">
        </form>
        <ul>
            <c:forEach items="${loginErrors}" var="loginError">
                <li><c:out value="${loginError}"/></li>
            </c:forEach>
        </ul>
    </fieldset>
    <fieldset>
        <legend>register</legend>
        <form:form action="/register" method="post" modelAttribute="user">
            <form:label path="firstName">first name
                <form:errors path="firstName"></form:errors>
                <form:input path="firstName"></form:input>
            </form:label><br>
            <form:label path="lastName">last name
                <form:errors path="lastName"></form:errors>
                <form:input path="lastName"></form:input>
            </form:label><br>
            <form:label path="email">email
                <form:errors path="email"></form:errors>
                <form:input path="email"></form:input>
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
            <form:label path="password">password
                <form:errors path="password"></form:errors>
                <form:input type="password" path="password"></form:input>
            </form:label><br>
            <form:label path="confirm">confirm
                <form:errors path="confirm"></form:errors>
                <form:input type="password" path="confirm"></form:input>
            </form:label><br>
            <input type="submit" value="register">
        </form:form>
        <ul>
            <c:if test="${emailExistsError != null}">
                <li><c:out value="${emailExistsError}"/></li>
            </c:if>
            <c:forEach items="${registrationErrors}" var="registrationError">
                <li><c:out value="${registrationError.getDefaultMessage()}"/></li>
            </c:forEach>
        </ul>
    </fieldset>
</body>
</html>