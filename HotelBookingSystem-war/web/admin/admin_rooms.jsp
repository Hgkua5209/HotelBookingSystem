<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard Portal</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; display: flex; background: #f8f9fa; }
        .sidebar { width: 260px; background: #2c3e50; color: white; min-height: 100vh; padding: 20px; box-sizing: border-box; }
        .sidebar h2 { text-align: center; font-size: 20px; margin-bottom: 30px; border-bottom: 2px solid #34495e; padding-bottom: 15px; }
        .sidebar a { display: block; color: #a6b0cf; padding: 12px 20px; text-decoration: none; border-radius: 4px; margin-bottom: 5px; font-weight: 500; }
        .sidebar a:hover { background: #34495e; color: white; }
        .content { flex-grow: 1; padding: 40px; }
        .cards-grid { display: flex; gap: 24px; margin-top: 30px; }
        .metric-card { flex: 1; background: white; padding: 25px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.05); position: relative; overflow: hidden; border-top: 4px solid #3498db; }
        .metric-card.guests { border-top-color: #f1c40f; }
        .metric-card.revenue { border-top-color: #2ecc71; }
        .metric-card h3 { margin: 0; color: #7f8c8d; font-size: 13px; text-transform: uppercase; letter-spacing: 1px; }
        .metric-card p { margin: 15px 0 0 0; font-size: 36px; font-weight: 700; color: #2c3e50; }
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Admin Control Unit</h2>
        <a href="AdminServlet?action=dashboard" style="background:#34495e; color:white;">Dashboard Metrics</a>
        <a href="AdminServlet?action=inventory">Room Inventory CRUD</a>
        <a href="AdminServlet?action=reservations">Global Reservations</a>
    </div>
    <div class="content">
        <h1>Administrative Operations Center</h1>
        <p style="color: #7f8c8d;">Live tracking summary metrics overview.</p>
        
        <div class="cards-grid">
            <div class="metric-card">
                <h3>Total Rooms Booked</h3>
                <p>${totalBooked}</p>
            </div>
            <div class="metric-card guests">
                <h3>Active Guests Checked-In</h3>
                <p>${activeGuests}</p>
            </div>
            <div class="metric-card revenue">
                <h3>Total Financial Revenue</h3>
                <p>RM ${totalRevenue}</p>
            </div>
        </div>
    </div>
</body>
</html>