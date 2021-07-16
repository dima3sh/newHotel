package org.azati.cources.dto;

import org.azati.cources.entity.Room;

import java.time.LocalDateTime;

public class GuestDTO {

    private String name;
    private String phoneNumber;
    private String emailAddress;
    private Long guestId;
    private Long guestRoomId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer invoice;


    public Long getRoom_id() {
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

    public void setRoomId(Long roomId) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getGuestRoomId() {
        return guestRoomId;
    }

    public void setGuestRoomId(Long guestRoomId) {
        this.guestRoomId = guestRoomId;
    }
}
