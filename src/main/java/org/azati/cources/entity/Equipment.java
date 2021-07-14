package org.azati.cources.entity;

import org.azati.cources.enums.StateEquipment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "equipment", schema = "public")
public class Equipment extends Thing {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "equipment_id")
    private Integer equipment_id;

    @Column(name = "equipment_status")
    private StateEquipment stateEquipment;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_room_id")
    private Room equipment_room_id;

    public Equipment() {
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public StateEquipment getStateEquipment() {
        return stateEquipment;
    }

    public Room getRoom_id() {
        return equipment_room_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public void setStateEquipment(StateEquipment stateEquipment) {
        this.stateEquipment = stateEquipment;
    }

    public void setRoom_id(Room room_id) {
        this.equipment_room_id = room_id;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipment_id=" + equipment_id +
                ", stateEquipment=" + stateEquipment +
                ", room_id=" + equipment_room_id +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return equipment_id.equals(equipment.equipment_id) && stateEquipment.equals(equipment.stateEquipment)
                && equipment_room_id.equals(equipment.equipment_room_id) && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(equipment_id);
        result = 31 * result + stateEquipment.hashCode();
        result = 31 * result + equipment_room_id.hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }
}
