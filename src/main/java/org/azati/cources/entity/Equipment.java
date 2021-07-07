package org.azati.cources.entity;

import org.azati.cources.enums.StateEquipment;

public class Equipment extends Thing {
    private Integer equipment_id;
    private StateEquipment stateEquipment;
    private Integer room_id;

    public Equipment(){}

    public Equipment(String name, Double weight, Integer costPerObject,
                     Integer equipment_id, StateEquipment stateEquipment, Integer room_id) {
        super(name, weight, costPerObject);
        this.equipment_id = equipment_id;
        this.stateEquipment = stateEquipment;
        this.room_id = room_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public StateEquipment getStateEquipment() {
        return stateEquipment;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public void setStateEquipment(StateEquipment stateEquipment) {
        this.stateEquipment = stateEquipment;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipment_id=" + equipment_id +
                ", stateEquipment=" + stateEquipment +
                ", room_id=" + room_id +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return equipment_id.equals(equipment.equipment_id) && stateEquipment.equals(equipment.stateEquipment)
                && room_id.equals(equipment.room_id) && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(equipment_id);
        result = 31 * result + stateEquipment.hashCode();
        result = 31 * result + Integer.hashCode(room_id);
        result = 31 * result + super.hashCode();
        return result;
    }
}
