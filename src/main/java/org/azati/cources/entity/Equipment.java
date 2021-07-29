package org.azati.cources.entity;

import org.azati.cources.dictionaries.EquipmentStateDictionary;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "equipment", schema = "public")
public class Equipment extends Thing {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "equipment_id")
    private Long equipmentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipment_state_id", nullable = false)
    private EquipmentStateDictionary equipmentStateDictionary;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_room_id", nullable = true)
    private Room equipmentRoomId;

    @Column(name = "producer")
    private String producer;

    public Room getEquipmentRoomId() {
        return equipmentRoomId;
    }

    public void setEquipmentRoomId(Room equipmentRoomId) {
        this.equipmentRoomId = equipmentRoomId;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Equipment() {
    }

    public Long getEquipment_id() {
        return equipmentId;
    }

    public EquipmentStateDictionary getEquipmentStateDictionary() {
        return equipmentStateDictionary;
    }

    public Room getRoom() {
        return equipmentRoomId;
    }

    public void setEquipment_id(Long equipment_id) {
        this.equipmentId = equipment_id;
    }

    public void setEquipmentStateDictionary(EquipmentStateDictionary equipmentStateDictionary) {
        this.equipmentStateDictionary = equipmentStateDictionary;
    }

    public void setRoom(Room room_id) {
        this.equipmentRoomId = room_id;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipment_id=" + equipmentId +
                ", equipmentStateDictionary=" + equipmentStateDictionary +
                ", equipment_room_id=" + equipmentRoomId.getRoomId() +
                ", producer='" + producer + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return equipmentId.equals(equipment.equipmentId) && equipmentStateDictionary.equals(equipment.equipmentStateDictionary)
                && equipmentRoomId.equals(equipment.equipmentRoomId) && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(equipmentId);
        result = 31 * result + equipmentStateDictionary.hashCode();
        result = 31 * result + equipmentRoomId.hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }
}
