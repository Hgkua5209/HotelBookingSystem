/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import entities.Rooms;
import entities.Bookings;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AdminBeanLocal {
    long getTotalRoomsBooked();
    long getActiveGuestsToday();
    BigDecimal getTotalRevenueEarned();
    List<Rooms> getAllRooms();
    void createRoom(Rooms room);
    Rooms getRoomById(String id);
    void updateRoom(Rooms room);
    void deleteRoom(String id);
    List<Bookings> getAllBookings();
    void changeRoomStatus(String roomId, String status);
    void cancelBookingOverride(int bookingId);
}
