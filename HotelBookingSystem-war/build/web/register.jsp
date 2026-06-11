<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hotel System - Register</title>
    </head>
    <body style="padding: 50px; font-family: Arial, sans-serif;">
        <h2>Create an Account</h2>
        <form action="AuthServlet" method="POST">
            <input type="hidden" name="action" value="register">
            
            <label>Username:</label><br>
            <input type="text" name="username" required><br><br>
            
            <label>Email:</label><br>
            <input type="email" name="email" required><br><br>
            
            <label>Password:</label><br>
            <input type="password" name="password" required><br><br>
            
            <button type="submit">Sign Up</button>
        </form>
        <br>
        <a href="login.jsp">Already have an account? Login here</a>
    </body>
</html>