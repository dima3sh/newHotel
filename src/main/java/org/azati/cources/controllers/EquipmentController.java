package org.azati.cources.controllers;

import org.azati.cources.dictionaries.EquipmentStateDictionary;
import org.azati.cources.dto.EquipmentDTO;
import org.azati.cources.dto.RoomDTO;
import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Room;
import org.azati.cources.enums.StateEquipment;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class EquipmentController {

    public static Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/add/equipment")
    public String addEquipment(Model model) {
        model.addAttribute("isEdit", false);
        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomService.getAllFreeRoom().forEach(room -> roomsDTO.add(DTOUtil.creteRoomDTO(room)));
        model.addAttribute("rooms", roomsDTO);
        model.addAttribute("warehouse", roomService.getRoom(20L));
        model.addAttribute("statesEquipment", StateEquipment.values());
        return "newequipment";
    }

    @RequestMapping(value = "/add/equipment", params = {"name", "weight", "state_equipment", "cost", "room_id", "equipment_id"}
            , method = RequestMethod.GET)
    public String editEquipment(Model model, @RequestParam("name") String name, @RequestParam("weight") Double weight
            , @RequestParam("state_equipment") Integer stateEquipment, @RequestParam("cost") Integer cost,
                                @RequestParam("room_id") Long room_id, @RequestParam("equipment_id") Long equipmentId) {
        model.addAttribute("isEdit", false);
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setWeight(weight);
        EquipmentStateDictionary equipmentStateDictionary = new EquipmentStateDictionary();
        equipmentStateDictionary.setLinkId(stateEquipment);
        equipmentStateDictionary.setStateEquipment(StateEquipment.values()[stateEquipment - 1]);
        equipment.setEquipmentStateDictionary(equipmentStateDictionary);
        equipment.setEquipment_id(equipmentId);
        if (room_id != null) {
            equipment.setRoom(roomService.getRoom(room_id));
        } else {
            Room room = new Room();
            room.setRoomId(0L);
            equipment.setRoom(new Room());
        }
        equipment.setCostPerObject(cost);
        equipmentService.updateEquipment(equipment);
        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        equipmentService.getAllEquipments().forEach(e -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(e)));
        model.addAttribute("equipments", equipmentsDTO);
        return "equipments";
    }

    @RequestMapping(value = "/add/equipment", params = {"name", "weight", "cost", "room_id"}, method = RequestMethod.GET)
    public String addEquipment(Model model, @RequestParam("name") String name, @RequestParam("weight") Double weight,
                               @RequestParam("cost") Integer cost, @RequestParam("room_id") Long room_id) {
        model.addAttribute("isEdit", false);
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setWeight(weight);
        EquipmentStateDictionary equipmentStateDictionary = new EquipmentStateDictionary();
        equipmentStateDictionary.setLinkId(StateEquipment.novel.getIndex());
        equipmentStateDictionary.setStateEquipment(StateEquipment.novel);
        equipment.setEquipmentStateDictionary(equipmentStateDictionary);
        if (room_id != null) {
            equipment.setRoom(roomService.getRoom(room_id));
        } else {
            Room room = new Room();
            room.setRoomId(0L);
            equipment.setRoom(new Room());
        }
        equipment.setCostPerObject(cost);
        equipmentService.addEquipment(equipment);
        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        equipmentService.getAllEquipments().forEach(e -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(e)));
        model.addAttribute("equipments", equipmentsDTO);
        return "equipments";
    }


    @RequestMapping(value = "/edit/equipment", params = {"id"}, method = RequestMethod.GET)
    public String addEquipment(Model model, @RequestParam("id") Long equipmentId) {
        model.addAttribute("isEdit", true);
        EquipmentDTO equipmentDTO = DTOUtil.createEquipmentDTO(equipmentService.getEquipment(equipmentId));
        model.addAttribute("equipment", equipmentDTO);
        model.addAttribute("statesEquipment", StateEquipment.values());
        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomService.getAllFreeRoom().forEach(room -> roomsDTO.add(DTOUtil.creteRoomDTO(room)));
        model.addAttribute("rooms", roomsDTO);
        model.addAttribute("warehouse", roomService.getRoom(20L));
        return "newequipment";
    }

    @RequestMapping(value = "/equipments", params = {"room_id"})
    public String getEquipments(Model model, @RequestParam(value = "room_id") Long roomId) {
        log.info("path : /equipments ; print all equipments");

        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        roomService.getEquipmentByRoomID(roomId).forEach(equipment -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(equipment)));
        model.addAttribute("equipments", equipmentsDTO);
        return "equipments";
    }

    @RequestMapping(value = "/equipments", params = {"id"})
    public String removeEquipmnet(Model model, @RequestParam(value = "id") Long equipmentId) {
        log.info("path : /equipments ; print all equipments");
        equipmentService.removeEquipment(equipmentId);
        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        equipmentService.getAllEquipments().forEach(equipment -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(equipment)));
        model.addAttribute("equipments", equipmentsDTO);
        return "equipments";
    }


    @RequestMapping(value = "/equipments")
    public String getEquipments(Model model) {
        log.info("path : /equipments ; print all equipments");

        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        equipmentService.getAllEquipments().forEach(equipment -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(equipment)));
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
