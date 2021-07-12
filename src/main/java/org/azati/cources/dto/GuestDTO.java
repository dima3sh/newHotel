package org.azati.cources.dto;

import org.azati.cources.entity.Room;

import java.time.LocalDateTime;

public class GuestDTO {

    private Long guest_id;
    private Room guest_room_id;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer invoice;


    public Room getRoom_id() {
        return guest_room_id;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setRoom_id(Room room_id) {
        this.guest_room_id = room_id;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public Long getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(Long guest_id) {
        this.guest_id = guest_id;
    }

}
