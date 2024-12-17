<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 12/16/2024
  Time: 11:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: sans-serif;
            margin: 20px;
        }

        form {
            max-width: 300px;
            margin: 0 auto;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"],
        input[type="reset"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="reset"] {
            background-color: #f44336;
        }
    </style>
</head>
<body>
    <h2 style="text-align: center">Login</h2>
    <form action="/login" method="post">
        Username: <input type="text" name="username" /><br>
        Password: <input type="password" name="password" /><br>
        <br>
        <br>
        <input type="submit" value="Login">
        <input type="reset" value="Reset">
    </form>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
            <h3 style="color: red">Invalid username or password!!</h3>
    <%
        }
    %>
</body>
</html>
