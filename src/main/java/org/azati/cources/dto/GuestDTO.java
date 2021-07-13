package org.azati.cources.dto;

import org.azati.cources.entity.Room;

import java.time.LocalDateTime;

public class GuestDTO {

    private Long guestId;
    private Room guestRoomId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer invoice;


    public Room getRoom_id() {
        return guestRoomId;
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

    public void setRoomId(Room roomId) {
        this.guestRoomId = roomId;
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

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

}
