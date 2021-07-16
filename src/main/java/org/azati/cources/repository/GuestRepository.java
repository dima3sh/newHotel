package org.azati.cources.repository;

import org.azati.cources.entity.Guest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface GuestRepository extends CrudRepository<Guest, Long> {

    @Modifying
    @Query(value = "INSERT INTO guest (guest_room_id, name_guest, phone_number," +
            " email_address, invoice, departure_time, arrival_time) VALUES" +
            " (:guest_room_id, :name_guest, :phone_number, :email_address," +
            " :invoice, :departure_time, :arrival_time)", nativeQuery = true)
    @Transactional
    public void addGuest(@Param("guest_room_id") Long roomId, @Param("name_guest") String nameGuest,
                         @Param("phone_number") String phoneNumber, @Param("email_address") String emailAddress,
                         @Param("invoice") Integer invoice, @Param("departure_time") LocalDateTime departureTime,
                         @Param("arrival_time") LocalDateTime arrivalTime);

    public List<Guest> findGuestsByName(String name);

    @Transactional
    public Integer deleteGuestByPhoneNumber(String phone);

    public List<Guest> findGuestByInvoice(Integer invoice);
}
