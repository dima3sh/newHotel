package org.azati.cources.services;

import org.azati.cources.dictionaries.EquipmentStateDictionary;
import org.azati.cources.entity.Equipment;
import org.azati.cources.enums.StateEquipment;
import org.azati.cources.repository.EquipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {

    public static Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    EquipmentRepository equipmentRepository;


    public Equipment addEquipment(Equipment equipment) {
        equipmentRepository.addEquipment(equipment.getRoom().getRoomId(), equipment.getName(),
                equipment.getWeight(), equipment.getCostPerObject(), equipment.getEquipmentStateDictionary().getLinkId(),
                equipment.getProducer());
        return equipment;
    }

    public Equipment getEquipment(Long equipmentId) {
        return equipmentRepository.findById(equipmentId).get();
    }

    public List<Equipment> getAllEquipments() {
        return (ArrayList<Equipment>) equipmentRepository.findAll();
    }

    public void removeEquipment(Long equipmentId) {
        equipmentRepository.deleteById(equipmentId);
    }

    public void updateEquipment(Equipment equipment) {
        Equipment upEquipment = equipmentRepository.findById(equipment.getEquipment_id()).get();
        upEquipment.setCostPerObject(equipment.getCostPerObject());
        upEquipment.setName(equipment.getName());
        upEquipment.setEquipmentStateDictionary(equipment.getEquipmentStateDictionary());
        upEquipment.setWeight(equipment.getWeight());
        upEquipment.setRoom(equipment.getRoom());
        upEquipment.setEquipment_id(equipment.getEquipment_id());
        equipmentRepository.save(upEquipment);
    }

    public Equipment equipmentFactory(String name, Double weight, String producer, StateEquipment stateEquipment, Integer cost) {
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setWeight(weight);
        equipment.setProducer(producer);
        EquipmentStateDictionary equipmentStateDictionary = new EquipmentStateDictionary();
        equipmentStateDictionary.setLinkId(stateEquipment.getIndex());
        equipmentStateDictionary.setStateEquipment(stateEquipment);
        equipment.setEquipmentStateDictionary(equipmentStateDictionary);
        equipment.setCostPerObject(cost);
        return equipment;
    }

    public List<Equipment> getEquipments(Integer numberPage, Integer countRecord, String sortBy) {
        Pageable elements = PageRequest.of(numberPage, countRecord, Sort.by(sortBy));
        Page<Equipment> allEquipments = equipmentRepository.findAll(elements);
        return allEquipments.getContent();
    }

    public Long getCountRecords() {
        return equipmentRepository.count();
    }
}
