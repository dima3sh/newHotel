package org.azati.cources.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "guest", schema = "public")
public class Guest extends Person {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "guest_id")
    private Long guest_id;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_room_id", nullable=false)
    private Room guest_room_id;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "invoice")
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

    @Override
    public String toString() {
        return "Guest{" +
                "room_id=" + guest_room_id.getRoom_id() +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", invoice=" + invoice +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return guest_room_id.equals(guest.guest_room_id) && departureTime.equals(guest.departureTime)
                && arrivalTime.equals(guest.arrivalTime) && invoice.equals(guest.invoice) && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = guest_room_id.hashCode();
        result = 31 * result + arrivalTime.hashCode();
        result = 31 * result + departureTime.hashCode();
        result = 31 * result + Integer.hashCode(invoice);
        result = 31 * result + super.hashCode();
        return result;
    }

}
