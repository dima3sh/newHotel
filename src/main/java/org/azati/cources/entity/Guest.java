package org.azati.cources.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "GUEST", schema = "public")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Guest extends Person {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "Guest_id")
    private Long guest_id;

    @Column(name = "Room_id")
    private Long room_id;

    @Column(name = "Departure_Time", insertable = false, updatable = false)
    private LocalDateTime departureTime;

    @Column(name = "Arrival_Time")
    private LocalDateTime arrivalTime;

    @Column(name = "Invoice")
    private Integer invoice;


    public Long getRoom_id() {
        return room_id;
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

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
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
                "room_id=" + room_id +
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
        return room_id.equals(guest.room_id) && departureTime.equals(guest.departureTime)
                && arrivalTime.equals(guest.arrivalTime) && invoice.equals(guest.invoice) && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(room_id);
        result = 31 * result + arrivalTime.hashCode();
        result = 31 * result + departureTime.hashCode();
        result = 31 * result + Integer.hashCode(invoice);
        result = 31 * result + super.hashCode();
        return result;
    }

}
