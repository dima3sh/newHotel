package org.azati.cources.entity;

import com.fasterxml.jackson.annotation.*;
import org.azati.cources.enums.StatusRoom;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "room_id")
    private Long room_id;

    @Column(name = "is_free_room")
    private Boolean isFreeRoom;

    @Column(name = "number_of_beds")
    private Integer numberOfBeds;

    @Column(name = "cost_per_hour")
    private Integer costPerHour;

    @Enumerated(EnumType.STRING)
    private StatusRoom statusRoom;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "equipment_room_id")
    private List<Equipment> equipments;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "guest_room_id")
    private List<Guest> guests;

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long idRoom) {
        this.room_id = idRoom;
    }

    public Boolean getFreeRoom() {
        return isFreeRoom;
    }

    public void setFreeRoom(Boolean freeRoom) {
        isFreeRoom = freeRoom;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Integer getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(Integer costPerHour) {
        this.costPerHour = costPerHour;
    }

    public StatusRoom getStatusRoom() {
        return statusRoom;
    }

    public void setStatusRoom(StatusRoom statusRoom) {
        this.statusRoom = statusRoom;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + room_id +
                ", isFreeRoom=" + isFreeRoom +
                ", numberOfBeds=" + numberOfBeds +
                ", costPerHour=" + costPerHour +
                ", statusRoom=" + statusRoom +
                ", equipments=" + equipments +
                ", guest=" + guests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return room_id.equals(room.room_id) && isFreeRoom.equals(room.isFreeRoom)
                && numberOfBeds.equals(room.numberOfBeds) && costPerHour.equals(room.costPerHour)
                && statusRoom.equals(room.statusRoom) && equipments.equals(room.equipments);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(room_id);
        result = 31 * result + isFreeRoom.hashCode();
        result = 31 * result + Integer.hashCode(numberOfBeds);
        result = 31 * result + Integer.hashCode(costPerHour);
        result = 31 * result + equipments.hashCode();
        return result;
    }
}
