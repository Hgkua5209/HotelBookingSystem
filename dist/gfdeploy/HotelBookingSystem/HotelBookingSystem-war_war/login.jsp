<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hotel System - Login</title>
    </head>
    <body style="padding: 50px; font-family: Arial, sans-serif;">
        <h2>User Login</h2>
        
        <%-- Displays an error message if login fails --%>
        <% if (request.getAttribute("error") != null) { %>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
        <% } %>

        <form action="AuthServlet" method="POST">
            <input type="hidden" name="action" value="login">
            
            <label>Username:</label><br>
            <input type="text" name="username" required><br><br>
            
            <label>Password:</label><br>
            <input type="password" name="password" required><br><br>
            
            <button type="submit">Login</button>
        </form>
        <br>
        <a href="register.jsp">Don't have an account? Register here</a>
    </body>
</html>