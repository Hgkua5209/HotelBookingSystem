<%-- 
    Document   : index
    Created on : 28 May 2026, 4:26:54 am
    Author     : User
--%>

<%-- 
    Document   : index
    Created on : 28 May 2026, 4:26:54 am
    Author     : User
--%>

<%@page import="entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hotel Booking System Skeleton</title>
    </head>
    <body style="padding: 50px; font-family: Arial, sans-serif;">
        <h1>Welcome to the Hotel Booking System</h1>
        <p>Framework status: Setup complete.</p>

        <hr style="border: 1px solid #ccc; margin: 20px 0;">

        <%-- Check if a user session exists --%>
        <%
            Users currentUser = (Users) session.getAttribute("currentUser");
            if (currentUser != null) {
        %>
            <h3 style="color: green;">Hello, <%= currentUser.getUsername()%>! You are successfully logged in.</h3>
            <p>Your Email: <%= currentUser.getEmail()%></p>
            
            <%-- Role 1 is Admin. If the logged-in user is an admin, show the control panel button --%>
            <% if ("1".equals(currentUser.getRole())) { %>
                <div style="background-color: #ebf5fb; border: 1px solid #3498db; padding: 15px; border-radius: 5px; margin-bottom: 20px; max-width: 400px;">
                    <h4 style="margin-top: 0; color: #2980b9;">👑 Administrative Control Panel</h4>
                    <p style="font-size: 14px; margin-bottom: 15px;">Access back-office metrics, room inventory CRUD, and global guest reservations.</p>
                    <a href="AdminServlet?action=dashboard" style="text-decoration: none;">
                        <button type="button" style="background-color: #3498db; color: white; border: none; padding: 10px 20px; font-weight: bold; cursor: pointer; border-radius: 4px;">
                            Go to Admin Dashboard
                        </button>
                    </a>
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/AuthServlet" method="POST">
                <input type="hidden" name="action" value="logout">
                <button type="submit" style="background-color: #ff4d4d; color: white; border: none; padding: 8px 15px; cursor: pointer;">Logout</button>
            </form>
        <% } else { %>
            <h3>Account Access</h3>
            <p>Please log in or create an account to start booking rooms.</p>
            <a href="login.jsp"><button style="padding: 8px 15px; cursor: pointer;">Go to Login</button></a>
            <a href="register.jsp"><button style="padding: 8px 15px; cursor: pointer;">Go to Register</button></a>
        <% }%>

        <hr style="border: 1px solid #ccc; margin: 20px 0;">

        <h3>System Diagnostics</h3>
        <form action="TestConnServlet" method="GET">
            <button type="submit">Test DB Connection & EJB Architecture</button>
        </form>
    </body>
</html>