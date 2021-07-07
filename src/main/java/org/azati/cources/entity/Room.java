package org.azati.cources.entity;

import org.azati.cources.enums.StatusRoom;

import java.util.List;
import java.util.Objects;

public class Room {
    private Integer room_id;
    private Boolean isFreeRoom;
    private Integer numberOfBeds;
    private Integer costPerHour;
    private StatusRoom statusRoom;
    private List<Equipment> equipments;

    public Room(){}

    public Room(Integer room_id, Boolean isFreeRoom, Integer numberOfBeds, Integer costPerHour,
                StatusRoom statusRoom, List<Equipment> equipments) {
        this.room_id = room_id;
        this.isFreeRoom = isFreeRoom;
        this.numberOfBeds = numberOfBeds;
        this.costPerHour = costPerHour;
        this.statusRoom = statusRoom;
        this.equipments = equipments;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer idRoom) {
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

    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + room_id +
                ", isFreeRoom=" + isFreeRoom +
                ", numberOfBeds=" + numberOfBeds +
                ", costPerHour=" + costPerHour +
                ", statusRoom=" + statusRoom +
                ", equipments=" + equipments +
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
        int result = Integer.hashCode(room_id);
        result = 31 * result + isFreeRoom.hashCode();
        result = 31 * result + Integer.hashCode(numberOfBeds);
        result = 31 * result + Integer.hashCode(costPerHour);
        result = 31 * result + equipments.hashCode();
        return result;
    }
}
