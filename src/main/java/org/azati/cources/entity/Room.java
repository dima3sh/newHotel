package org.azati.cources.entity;

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
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "is_free_room")
    private Boolean isFreeRoom;

    @Column(name = "number_of_beds")
    private Integer numberOfBeds;

    @Column(name = "cost_per_hour")
    private Integer costPerHour;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_room_id", nullable = false)
    private DirectoryStatus directoryStatus;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "equipment_room_id")
    private List<Equipment> equipments;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "guest_room_id")
    private List<Guest> guests;


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long idRoom) {
        this.roomId = idRoom;
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

    public DirectoryStatus getDirectoryStatus() {
        return directoryStatus;
    }

    public void setDirectoryStatus(DirectoryStatus directoryStatus) {
        this.directoryStatus = directoryStatus;
    }

    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + roomId +
                ", isFreeRoom=" + isFreeRoom +
                ", numberOfBeds=" + numberOfBeds +
                ", costPerHour=" + costPerHour +
                ", equipments=" + equipments +
                ", guest=" + guests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return roomId.equals(room.roomId) && isFreeRoom.equals(room.isFreeRoom)
                && numberOfBeds.equals(room.numberOfBeds) && costPerHour.equals(room.costPerHour)
                && equipments.equals(room.equipments);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(roomId);
        result = 31 * result + isFreeRoom.hashCode();
        result = 31 * result + Integer.hashCode(numberOfBeds);
        result = 31 * result + Integer.hashCode(costPerHour);
        result = 31 * result + equipments.hashCode();
        return result;
    }
}
