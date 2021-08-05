package org.azati.cources.dictionaries;

import org.azati.cources.entity.Equipment;
import org.azati.cources.enums.StateEquipment;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "linked_equipment_state")
public class EquipmentStateDictionary {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "link_id")
    private Integer linkId;

    @Column(name = "varchar_equipment_state")
    @Enumerated(EnumType.STRING)
    private StateEquipment stateEquipment;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "equipmentStateDictionary")
    private List<Equipment> equipments;

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public StateEquipment getStateEquipment() {
        return stateEquipment;
    }

    public void setStateEquipment(StateEquipment stateEquipment) {
        this.stateEquipment = stateEquipment;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }
}
