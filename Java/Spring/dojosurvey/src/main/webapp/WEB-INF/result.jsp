<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>survey form</title>
    </head>
    <body>
        <h1>submitted information</h1>
        <h3>name: <c:out value="${name}"/></h3>
        <h3>location: <c:out value="${location}"/></h3>
        <h3>language: <c:out value="${language}"/></h3>
        <h3>comment: <c:out value="${comment}"/></h3>
    </body>
</html>