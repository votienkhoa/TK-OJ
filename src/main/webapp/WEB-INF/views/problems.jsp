<%@ page import="model.bean.Problem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 12/17/2024
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Problems</title>
    <style>
        body {
            font-family: 'Open Sans', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;

        }
        h1 {
            text-align: center;
            color: #333;
            padding: 20px 0;
            background-color: #4CAF50;
            color: white;
            margin-bottom: 30px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            overflow: hidden;
        }
        th {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            text-align: left;
        }

        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
    <h1>Problem List</h1>

    <a href="/submissions">Go to submissions</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
        <%
            ArrayList<Problem> problems = (ArrayList<Problem>) request.getAttribute("problems");
            if (problems != null && !problems.isEmpty()) {
                for (Problem problem : problems) {
        %>
        <tr>
            <td><%= problem.getId() %></td>
            <td><a href="/problems/<%=problem.getId()%>"><%= problem.getTitle() %></a></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3">No problems available</td>
        </tr>
        <% } %>
        </tbody>
    </table>

</body>
</html>
