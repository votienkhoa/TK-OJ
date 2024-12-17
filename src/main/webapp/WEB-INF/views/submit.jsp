<%@ page import="java.util.HashMap" %>
<%@ page import="model.bean.Problem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Submit</title>
    <style>
        body {
            font-family: 'Open Sans', sans-serif;
            margin: 20px;
            background-color: #f0f0f0;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        h3 {
            color: #555;
        }

        form {
            width: 80%;
            max-width: 600px;
            margin: 0 auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        label {
            font-weight: bold;
            margin-top: 15px;
            display: block;
        }

        select, textarea, button {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        textarea {
            resize: vertical;
            height: 200px;
        }

        button {
            background-color: #007BFF;
            color: #fff;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
            transition: background-color 0.3s ease; /* Smooth hover effect */
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<% Problem problem = (Problem) request.getAttribute("problem"); %>
<h1>TKOJ Submit Solution</h1>
<h3>Problem: </h3>
<h4><%= problem.getTitle() %></h4>
<form action="submit" method="post">
    <input type="hidden" name="problemId" value="<%= problem.getId() %>" />

    <label for="language">Select Language:</label>
    <select name="language" id="language">
        <%
            HashMap<Integer, String> languages = (HashMap<Integer, String>) request.getAttribute("languages");
            if (languages != null && !languages.isEmpty()) {
                for (Integer id : languages.keySet()) {
        %>
        <option value="<%= id %>"><%= languages.get(id) %></option>
        <%
            }
        } else {
        %>
        <option value="" disabled>No languages available</option>
        <%
            }
        %>
    </select>

    <br><br>
    <label for="sourceCode">Enter your code:</label><br>
    <textarea name="sourceCode" id="sourceCode" rows="15" cols="70" placeholder="Write your code here..."></textarea>
    <br><br>

    <button type="submit">Submit</button>
</form>
</body>
</html>
