<%@ page import="model.bean.Problem" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 12/14/2024
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Problem Details</title>
    <style>
        button{
            font-family: Arial, sans-serif;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            height: 50px;
            width: 150px;
            background-color: #007BFF;
        }
        button:hover{
            background-color: #0056b3;
        }
        .submit{
            position: absolute;
            right: 325px;
            top: 50px;
        }
        body {
            font-family: 'Open Sans', sans-serif;
            margin: 20px;
        }
        .problem-container {
            display: flex;
            flex-direction: column;
            max-width: 900px;
            margin: 0 auto;
        }
        h1 {
            color: #333;
        }
        pre {
            font-size: 15px;
            white-space: pre-wrap;
            background-color: #f4f4f4;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="problem-container">
    <%
        Problem problem = (Problem) request.getAttribute("problem");
        if (problem != null) {
    %>
            <div class="header">
                <h1><%= problem.getTitle() %></h1>
                <p>Time limit: <%= problem.getTime_limit()%>s</p>
                <p>Memory limit: <%= problem.getMemory_limit()%>kB</p>
            </div>
            <form class="submit" action="/submit" method="get">
                <input type="hidden" name="problemId" value="<%= problem.getId() %>" />
                <button type="submit">Submit Solution</button>
            </form>
            <p><strong>Description:</strong></p>
            <pre><%= problem.getDescription() %></pre>

            <p><strong>Input:</strong></p>
            <pre><%= problem.getInputFormat() %></pre>

            <p><strong>Output:</strong></p>
            <pre><%= problem.getOutputFormat() %></pre>

            <p><strong>Sample Input:</strong></p>
            <pre><%= problem.getInputSample() %></pre>

            <p><strong>Sample Output:</strong></p>
            <pre><%= problem.getOutputSample() %></pre>


    <%
        }
        else {
    %>
            <p>Problem not found.</p>
    <%
        }
    %>
</div>
</body>
</html>
