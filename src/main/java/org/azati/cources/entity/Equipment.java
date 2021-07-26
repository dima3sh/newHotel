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
    private Long equipment_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipment_state_id", nullable = false)
    private EquipmentStateDictionary equipmentStateDictionary;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_room_id", nullable = true)
    private Room equipment_room_id;

    @Column(name = "producer")
    private String producer;

    public Room getEquipment_room_id() {
        return equipment_room_id;
    }

    public void setEquipment_room_id(Room equipment_room_id) {
        this.equipment_room_id = equipment_room_id;
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
        return equipment_id;
    }

    public EquipmentStateDictionary getEquipmentStateDictionary() {
        return equipmentStateDictionary;
    }

    public Room getRoom() {
        return equipment_room_id;
    }

    public void setEquipment_id(Long equipment_id) {
        this.equipment_id = equipment_id;
    }

    public void setEquipmentStateDictionary(EquipmentStateDictionary equipmentStateDictionary) {
        this.equipmentStateDictionary = equipmentStateDictionary;
    }

    public void setRoom(Room room_id) {
        this.equipment_room_id = room_id;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipment_id=" + equipment_id +
                ", equipmentStateDictionary=" + equipmentStateDictionary +
                ", equipment_room_id=" + equipment_room_id +
                ", producer='" + producer + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return equipment_id.equals(equipment.equipment_id) && equipmentStateDictionary.equals(equipment.equipmentStateDictionary)
                && equipment_room_id.equals(equipment.equipment_room_id) && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(equipment_id);
        result = 31 * result + equipmentStateDictionary.hashCode();
        result = 31 * result + equipment_room_id.hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }
}
