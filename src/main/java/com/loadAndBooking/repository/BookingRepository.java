package com.loadAndBooking.repository;

import com.loadAndBooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByLoad_ShipperId(String shipperId); // based on associated Load
    List<Booking> findByTransporterId(String transporterId);
}