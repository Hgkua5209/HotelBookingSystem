package controllers;

import entities.Users;
import services.UserBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AuthServlet", urlPatterns = {"/AuthServlet"})
public class AuthServlet extends HttpServlet {

    @EJB
    private UserBean userBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // === 1. REGISTER ACTION ===
        if ("register".equals(action)) {
            Users newUser = new Users();
            newUser.setUsername(request.getParameter("username"));
            newUser.setEmail(request.getParameter("email"));
            newUser.setPassword(request.getParameter("password"));

            // Set default role: 1 for admin, 2 for user
            newUser.setRole("2");

            // Send to EJB to save in database
            userBean.registerUser(newUser);

            // Take them to login page
            response.sendRedirect("login.jsp");
            return; // Stops execution
            
        // === 2. LOGIN ACTION ===
        } else if ("login".equals(action)) {
            String uname = request.getParameter("username");
            String pword = request.getParameter("password");

            // Ask EJB if this user exists
            Users authenticatedUser = userBean.loginUser(uname, pword);

            if (authenticatedUser != null) {
                // SUCCESS: Save user session data
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", authenticatedUser);

                // Redirect back to index.jsp
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return; // Stops execution
            } else {
                // FAILURE: Show error on login page
                request.setAttribute("error", "Invalid username or password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return; // Stops execution
            }

        // === 3. LOGOUT ACTION ===
        } else if ("logout".equals(action)) {
            // Grab the current session if it exists, do not create a new one
            HttpSession session = request.getSession(false);
            
            if (session != null) {
                session.invalidate(); // Destroys the logged-in session data completely
            }
            
            // Safe redirect forcing the app to drop back to the main landing index page
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return; // Stops execution here so nothing hangs on a blank screen
        }
    }
}