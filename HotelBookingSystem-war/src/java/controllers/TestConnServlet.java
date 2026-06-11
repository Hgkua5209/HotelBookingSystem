/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserBean;

/**
 *
 * @author User
 */
@WebServlet(name = "TestConnServlet", urlPatterns = {"/TestConnServlet"})
public class TestConnServlet extends HttpServlet {

    @EJB
    private UserBean userBean; // Injecting your reusable EJB asset

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>DB Test</title></head>");
            out.println("<body>");
            try {
                // Triggering a lightweight query logic through your EJB
                boolean isConnected = userBean.testDatabaseConnection(); 
                
                if (isConnected) {
                    out.println("<h2 style='color:green;'>Success: Connected to Database & EJB Architecture works!</h2>");
                }
            } catch (Exception e) {
                out.println("<h2 style='color:red;'>Error connecting to EJB or DB:</h2>");
                out.println("<pre>" + e.getMessage() + "</pre>");
            }
            out.println("<br><a href='index.jsp'>Go Back</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirection to doGet if they attempt a POST request on this test link
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Database Connection Test Servlet";
    }
}