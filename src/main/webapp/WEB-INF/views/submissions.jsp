<%@ page import="model.bean.Submission" %>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.User" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Collections" %>
<%@ page import="model.bean.Language" %>
<%@ page import="model.bo.LanguageBO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="model.bean.Problem" %>
<%@ page import="model.bo.ProblemBO" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 12/16/2024
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Submissions</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
            line-height: 1.6;
        }
        h1, h2 {
            text-align: center;
            color: #444;
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

        th, td {
            padding: 12px 15px;
            text-align: center;
            border: 1px solid #ddd;
        }

        thead {
            background-color: #007BFF;
            color: #fff;
        }

        tbody tr:hover {
            background-color: #e0f7fa;
        }

        th {
            font-weight: bold;
        }
        .nav {
            display: block;
            text-align: center;
            margin: 20px auto;
            text-decoration: none;
            color: #fff;
            background-color: #28a745;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            width: 200px;
        }
        .problem{
            text-decoration: none;
        }
        .nav:hover {
            background-color: #218838;
        }
        h2 {
            background: #f8f9fa;
            padding: 10px;
            border-radius: 5px;
            width: 300px;
            margin: 0 auto 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <h1>My Submissions</h1>
    <%
        User user = (User) session.getAttribute("user");
    %>
    <h2>Username: <%= user.getUsername()%></h2>
    <p style="margin-left: 100px">Note: Please reload page to see the newest status</p>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Problem</th>
                <th>Language</th>
                <th>Status</th>
                <th>Created At</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Submission> submissions = (List<Submission>) request.getAttribute("submissions");
            HashMap<Integer, String> languages = (HashMap<Integer, String>) request.getAttribute("languages");
            HashMap<Integer, String> problems = (HashMap<Integer, String>) request.getAttribute("problems");
            if (submissions != null && !submissions.isEmpty()) {
                Collections.reverse(submissions);
                for (Submission submission : submissions) {
        %>
        <tr>
            <td><%= submission.getId() %></td>
            <td><a class="problem" href="/problems/<%=submission.getProblemId()%>"><%=problems.get(submission.getProblemId())%></a></td>
            <td><%= languages.get(submission.getLanguage()) %></td>
            <td style="color:
                <%= submission.getStatus().equals("Accepted") ? "green" :
                    (submission.getStatus().equals("Pending") ? "grey" : "red") %>">
                <%= submission.getStatus() %>
            </td>
            <td><%= submission.getCreatedAt() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4">No submissions found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <a class="nav" href="problems/">Go to problems page</a>
</body>
</html>

