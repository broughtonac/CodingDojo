<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>ninja gold</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <style>
            .history {height: 300px; width: 500px; overflow: scroll;}
            .positive {color: green;}
            .negative {color: red;}
        </style>
    </head>
    <body>
        <div class="wrapper">
            <div class="balance">
                your gold: <span><c:out value="${gold}"/></span>
            </div>
            <div class="place">
                <h1>farm</h1>
                <p>earns 10-20 gold</p>
                <form action="/process" method="post">
                    <input type="hidden" name="building" value="farm">
                    <input type="submit" value="find gold">
                </form>
            </div>
            <div class="place">
                <h1>cave</h1>
                <p>earns 5-10 gold</p>
                <form action="/process" method="post">
                    <input type="hidden" name="building" value="cave">
                    <input type="submit" value="find gold">
                </form>
            </div>
            <div class="place">
                <h1>house</h1>
                <p>earns 2-5 gold</p>
                <form action="/process" method="post">
                    <input type="hidden" name="building" value="house">
                    <input type="submit" value="find gold">
                </form>
            </div>
            <div class="place">
                <h1>casino</h1>
                <p>earns/loses 0-50 gold</p>
                <form action="/process" method="post">
                    <input type="hidden" name="building" value="casino">
                    <input type="submit" value="find gold">
                </form>
            </div>
            <fieldset class="history">
                <legend>activity</legend>
                    <% ArrayList<String> activity = (ArrayList<String>) session.getAttribute("activity"); %>
                    <% ArrayList<String> nets = (ArrayList<String>) session.getAttribute("nets"); %>
                    <% ArrayList<String> times = (ArrayList<String>) session.getAttribute("times"); %>
                    <% for (int i = 0; i < activity.size(); i++) { %>
                        <% String action = activity.get(i); %>
                        <% String net = nets.get(i); %>
                        <% String time = times.get(i); %>
                        <p class=<%= net %>><%= action %> <%= time %></p>
                    <% } %>
            </fieldset>
            <form action="/reset" method="post">
                <input type="submit" value="reset">
            </form>
        </div>
    </body>
</html>