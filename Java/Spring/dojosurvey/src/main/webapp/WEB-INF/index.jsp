<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>survey form</title>
    </head>
    <body>
        <form action='/process' method='post'>
            name: <input type='text' name='name'><br><br>
            location: <select name="location">
                <option value="seattle">seattle</option>
                <option value="chicago">chicago</option>
                <option value="dc">dc</option>
            </select><br><br>
            favorite language: <select name="language">
                <option value="python">python</option>
                <option value="java">java</option>
                <option value="haskell">haskell</option>
            </select><br><br>
            comment: <input type="text" name="comment"><br><br>
            <input type="submit" value="enter data">
        </form>
    </body>
</html>