package org.azati.cources.controllers;

import org.azati.cources.dictionaries.EquipmentStateDictionary;
import org.azati.cources.dto.EquipmentDTO;
import org.azati.cources.dto.RoomDTO;
import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Room;
import org.azati.cources.services.EquipmentService;
import org.azati.cources.services.RoomService;
import org.azati.cources.utils.DTOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EquipmentController {

    public static Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    RoomService roomService;

    @ResponseBody
    @RequestMapping(value = "/newequipment")
    public String addEquipment() {
        EquipmentStateDictionary equipmentStateDictionary = new EquipmentStateDictionary();
        equipmentStateDictionary.setLinkId(1);
        Room room = new Room();
        room.setRoomId(1L);
        Equipment equipment = new Equipment();
        equipment.setEquipmentStateDictionary(equipmentStateDictionary);
        equipment.setEquipment_id(1L);
        equipment.setRoom(room);
        equipment.setName("TV");
        equipment.setWeight(12.3);
        equipment.setCostPerObject(12);
        equipmentService.addEquipment(equipment);
        return "add equipment successful";
    }

    @RequestMapping(value = "/equipments", params = {"room_id"})
    public String getEquipments(Model model, @RequestParam(value = "room_id") Long roomId) {
        log.info("path : /equipments ; print all equipments");

        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        roomService.getEquipmentByRoomID(roomId).forEach(equipment -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(equipment)));
        model.addAttribute("equipments", equipmentsDTO);
        return "equipments";
    }

    @RequestMapping(value = "/equipments", params = {"equipment_id", "room_id"}, method = RequestMethod.GET)
    public String getRooms(Model model, @RequestParam(value = "equipment_id") Long equipmentId,
                           @RequestParam(value = "room_id") Long roomId) {
        equipmentService.removeEquipment(equipmentId);
        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        roomService.getEquipmentByRoomID(roomId).forEach(equipment -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(equipment)));
        model.addAttribute("equipments", equipmentsDTO);
        return "equipments";
    }
}
