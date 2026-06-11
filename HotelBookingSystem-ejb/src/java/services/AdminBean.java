/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package services;

import javax.ejb.Stateless;
import javax.ejb.Local; // <-- IMPORT LOCAL INTERFACE CONTAINER
import entities.Rooms;
import entities.Bookings;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AMM
 */
@Stateless
@Local // <-- SWAP @LocalBean TO @Local HERE
public class AdminBean implements AdminBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // ==========================================
    // 1. CENTRAL ADMINISTRATION DASHBOARD METRICS
    // ==========================================
    
    public long getTotalRoomsBooked() {
        try {
            return (long) em.createNamedQuery("Bookings.findAll").getResultList().size();
        } catch (Exception e) {
            return 0;
        }
    }

    public long getActiveGuestsToday() {
        try {
            return (long) em.createQuery("SELECT COUNT(r) FROM Rooms r WHERE r.status = 'Occupied'").getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public BigDecimal getTotalRevenueEarned() {
        try {
            BigDecimal total = (BigDecimal) em.createQuery("SELECT SUM(b.totalPrice) FROM Bookings b").getSingleResult();
            return total != null ? total : BigDecimal.ZERO;
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    // ==========================================
    // 2. ROOM INVENTORY CRUD INTERFACE
    // ==========================================
    
    public List<Rooms> getAllRooms() {
        return em.createNamedQuery("Rooms.findAll", Rooms.class).getResultList();
    }

    public void createRoom(Rooms room) {
        em.persist(room);
    }

    public Rooms getRoomById(String id) {
        return em.find(Rooms.class, id);
    }

    public void updateRoom(Rooms room) {
        em.merge(room);
    }

    public void deleteRoom(String id) {
        Rooms room = em.find(Rooms.class, id);
        if (room != null) {
            em.remove(room);
        }
    }

    // ==========================================
    // 3. GLOBAL RESERVATION MANAGEMENT & OVERRIDES
    // ==========================================
    
    public List<Bookings> getAllBookings() {
        return em.createNamedQuery("Bookings.findAll", Bookings.class).getResultList();
    }

    public void changeRoomStatus(String roomId, String status) {
        Rooms room = em.find(Rooms.class, roomId);
        if (room != null) {
            room.setStatus(status);
            em.merge(room);
        }
    }

    public void cancelBookingOverride(int bookingId) {
        Bookings booking = em.find(Bookings.class, bookingId);
        if (booking != null) {
            changeRoomStatus(booking.getRoomType(), "Available");
            em.remove(booking);
        }
    }
}