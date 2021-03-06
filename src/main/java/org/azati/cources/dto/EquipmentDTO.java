package org.azati.cources.dto;

import org.azati.cources.enums.StateEquipment;

public class EquipmentDTO {


    private Long equipmentId;
    private StateEquipment stateEquipment;
    private Long equipmentRoomId;
    protected String name;
    protected Double weight;
    protected Integer costPerObject;
    private String producer;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentRoomId() {
        return equipmentRoomId;
    }

    public void setEquipmentRoomId(Long equipmentRoomId) {
        this.equipmentRoomId = equipmentRoomId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getCostPerObject() {
        return costPerObject;
    }

    public void setCostPerObject(Integer costPerObject) {
        this.costPerObject = costPerObject;
    }

    public StateEquipment getStateEquipment() {
        return stateEquipment;
    }

    public void setStateEquipment(StateEquipment stateEquipment) {
        this.stateEquipment = stateEquipment;
    }


}
