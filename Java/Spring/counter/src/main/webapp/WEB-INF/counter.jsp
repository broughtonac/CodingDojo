<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>visit count</title>
</head>
<body>
    <h3>you've visited http://127.0.0.1:8080 <c:out value="${count}"/> times</h3>
    <p><a href="/">test another visit</a></p>
    <p><a href="clear">reset counter</a></p>
</body>
</html>