package controllers;

import entities.Rooms;
import services.AdminBean;
import java.io.IOException;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AdminBeanLocal;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    @EJB //(mappedName = "java:global/HotelBookingSystem/HotelBookingSystem-ejb/AdminBean!services.AdminBean")
    private AdminBeanLocal adminBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "dashboard";
        }

        switch (action) {
            case "inventory":
                request.setAttribute("roomsList", adminBean.getAllRooms());
                request.getRequestDispatcher("admin_rooms.jsp").forward(request, response);
                break;
                
            case "reservations":
                request.setAttribute("bookingsList", adminBean.getAllBookings());
                request.getRequestDispatcher("admin_reservations.jsp").forward(request, response);
                break;
                
            case "dashboard":
            default:
                request.setAttribute("totalBooked", adminBean.getTotalRoomsBooked());
                request.setAttribute("activeGuests", adminBean.getActiveGuestsToday());
                request.setAttribute("totalRevenue", adminBean.getTotalRevenueEarned());
                request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("addRoom".equals(action)) {
            Rooms r = new Rooms();
            r.setId(request.getParameter("id"));
            r.setType(request.getParameter("type"));
            r.setPrice(Double.parseDouble(request.getParameter("price")));
            r.setStatus(request.getParameter("status"));
            r.setCapacity(request.getParameter("capacity"));
            r.setBeds(request.getParameter("beds"));
            r.setAmenities(request.getParameter("amenities"));
            
            adminBean.createRoom(r);
            response.sendRedirect("AdminServlet?action=inventory");

        } else if ("updateRoom".equals(action)) {
            String id = request.getParameter("id");
            Rooms r = adminBean.getRoomById(id);
            if (r != null) {
                r.setType(request.getParameter("type"));
                r.setPrice(Double.parseDouble(request.getParameter("price")));
                r.setStatus(request.getParameter("status"));
                r.setCapacity(request.getParameter("capacity"));
                r.setBeds(request.getParameter("beds"));
                r.setAmenities(request.getParameter("amenities"));
                adminBean.updateRoom(r);
            }
            response.sendRedirect("AdminServlet?action=inventory");

        } else if ("deleteRoom".equals(action)) {
            String id = request.getParameter("id");
            adminBean.deleteRoom(id);
            response.sendRedirect("AdminServlet?action=inventory");

        } else if ("quickStatusUpdate".equals(action)) {
            String roomId = request.getParameter("roomId");
            String status = request.getParameter("status");
            adminBean.changeRoomStatus(roomId, status);
            response.sendRedirect("AdminServlet?action=reservations");

        } else if ("cancelBooking".equals(action)) {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            adminBean.cancelBookingOverride(bookingId);
            response.sendRedirect("AdminServlet?action=reservations");
        }
    }
}