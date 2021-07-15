package org.azati.cources.services;

import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Room;
import org.azati.cources.repository.EquipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {

    public static Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    EquipmentRepository equipmentRepository;


    public Equipment addEquipment(Equipment equipment) {
        log.info("add room : " + equipment.toString());
        equipmentRepository.addEquipment(equipment.getRoom().getRoomId(), equipment.getName(),
                equipment.getWeight(), equipment.getCostPerObject(), equipment.getEquipmentStateDictionary().getLinkId());
        return equipment;
    }

    public List<Equipment> getAllEquipments() {
        return (ArrayList<Equipment>)equipmentRepository.findAll();
    }

    public void removeEquipment(Long equipmentId) {
        equipmentRepository.deleteById(equipmentId);
    }


}
