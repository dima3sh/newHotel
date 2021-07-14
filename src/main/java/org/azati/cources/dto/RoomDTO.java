package org.azati.cources.dto;

import org.azati.cources.entity.DirectoryStatus;
import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Guest;
import org.azati.cources.enums.StatusRoom;

import java.util.List;

public class RoomDTO {

    private Long roomId;
    private Boolean isFreeRoom;
    private Integer numberOfBeds;
    private Integer costPerHour;
    private StatusRoom statusRoom;
    private List<Equipment> equipments;
    private List<Guest> guests;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long idRoom) {
        this.roomId = idRoom;
    }

    public Boolean getIsFreeRoom() {
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

}
