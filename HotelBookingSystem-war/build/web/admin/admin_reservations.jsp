<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Global Master Reservations Control</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; margin: 0; display: flex; background: #f8f9fa; }
        .sidebar { width: 260px; background: #2c3e50; color: white; min-height: 100vh; padding: 20px; box-sizing: border-box; }
        .sidebar a { display: block; color: #a6b0cf; padding: 12px 20px; text-decoration: none; border-radius: 4px; margin-bottom: 5px; }
        .sidebar a:hover { background: #34495e; color: white; }
        .content { flex-grow: 1; padding: 40px; box-sizing: border-box; }
        table { width: 100%; border-collapse: collapse; background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 12px rgba(0,0,0,0.05); }
        th, td { padding: 14px 20px; text-align: left; font-size: 14px; }
        th { background: #34495e; color: white; text-transform: uppercase; font-size: 12px; }
        td { border-bottom: 1px solid #f1f2f6; }
        .btn-inline-set { background: #f1c40f; border: none; padding: 6px 12px; font-weight: bold; cursor: pointer; border-radius: 4px; color: #2c3e50; }
        .btn-cancel-reservation { background: #e74c3c; border: none; color: white; padding: 6px 12px; cursor: pointer; border-radius: 4px; font-weight: bold; }
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Admin Control Unit</h2>
        <a href="AdminServlet?action=dashboard">Dashboard Metrics</a>
        <a href="AdminServlet?action=inventory">Room Inventory CRUD</a>
        <a href="AdminServlet?action=reservations" style="background:#34495e; color:white;">Global Reservations</a>
    </div>
    <div class="content">
        <h1>Global System Bookings Master Control</h1>
        <p style="color: #7f8c8d;">Override system entries and execute immediate manual Check-In or Check-Out controls.</p>
        
        <table>
            <thead>
                <tr>
                    <th>Reservation ID</th>
                    <th>Client (User ID Obj)</th>
                    <th>Room Code</th>
                    <th>Check-In Date</th>
                    <th>Check-Out Date</th>
                    <th>Total Price</th>
                    <th>Live Room State Override</th>
                    <th>System Termination</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${bookingsList}">
                    <tr>
                        <td># ${book.id}</td>
                        <td>Guest ID: ${book.userId.id} (${book.userId.username})</td>
                        <td>Room <strong>${book.roomType}</strong></td>
                        <td>${book.checkInDate}</td>
                        <td>${book.checkOutDate}</td>
                        <td><strong>RM ${book.totalPrice}</strong></td>
                        <td>
                            <form action="AdminServlet" method="POST" style="display:inline;">
                                <input type="hidden" name="roomId" value="${book.roomType}">
                                <select name="status" style="padding: 4px; border-radius: 4px;">
                                    <option value="Occupied">Checked-In (Occupied)</option>
                                    <option value="Available">Checked-Out (Available)</option>
                                    <option value="Under Maintenance">Maintenance Lock</option>
                                </select>
                                <button type="submit" name="action" value="quickStatusUpdate" class="btn-inline-set">Apply</button>
                            </form>
                        </td>
                        <td>
                            <form action="AdminServlet" method="POST" style="display:inline;">
                                <input type="hidden" name="bookingId" value="${book.id}">
                                <button type="submit" name="action" value="cancelBooking" class="btn-cancel-reservation" onclick="return confirm('Forcibly drop this booking transaction entirely?');">Cancel</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>