package org.azati.cources.controllers;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class EquipmentController {

    public static Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    RoomService roomService;

    @Value("${warehouse.id}")
    private Long warehouseId;

    @RequestMapping(value = "/add/equipment")
    public String addEquipment(Model model) {

        model.addAttribute("isEdit", false);
        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomService.getAllFreeRoom().forEach(room -> roomsDTO.add(DTOUtil.creteRoomDTO(room)));
        model.addAttribute("rooms", roomsDTO);
        try {
            model.addAttribute("warehouse", roomService.getRoom(warehouseId));
        } catch (RuntimeException ex) {

        }
        model.addAttribute("statesEquipment", StateEquipment.values());
        model.addAttribute("warehouseId", warehouseId);
        return "newequipment";
    }

    @RequestMapping(value = "/add/equipment", params = {"name", "weight", "producer", "state_equipment", "cost", "room_id", "equipment_id"}
            , method = RequestMethod.GET)
    public String editEquipment(Model model, @RequestParam("name") String name, @RequestParam("weight") Double weight
            , @RequestParam("producer") String producer
            , @RequestParam("state_equipment") Integer stateEquipment
            , @RequestParam("cost") Integer cost
            , @RequestParam("room_id") Long room_id
            , @RequestParam("equipment_id") Long equipmentId
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "equipmentId") String sortBy) {

        model.addAttribute("isEdit", false);
        Equipment equipment = equipmentService.equipmentFactory(name, weight, producer,
                StateEquipment.values()[stateEquipment - 1], cost);
        if (room_id != null) {
            equipment.setRoom(roomService.getRoom(room_id));
        } else {
            Room room = new Room();
            room.setRoomId(0L);
            equipment.setRoom(new Room());
        }
        equipment.setEquipment_id(equipmentId);
        equipment.setCostPerObject(cost);
        equipmentService.updateEquipment(equipment);
        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        equipmentService.getEquipments(page - 1, size, sortBy).forEach(e -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(e)));
        model.addAttribute("equipments", equipmentsDTO);
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("location", "equipments");
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        model.addAttribute("countPages", Math.ceil(equipmentService.getCountRecords() * 1.0 / size));
        return "equipments";
    }

    @RequestMapping(value = "/add/equipment", params = {"name", "weight", "producer", "cost", "room_id"}, method = RequestMethod.GET)
    public String addEquipment(Model model, @RequestParam("name") String name
            , @RequestParam("weight") Double weight
            , @RequestParam("producer") String producer
            , @RequestParam("cost") Integer cost
            , @RequestParam("room_id") Long room_id
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "equipmentId") String sortBy) {

        model.addAttribute("isEdit", false);
        Equipment equipment = equipmentService.equipmentFactory(name, weight, producer, StateEquipment.novel, cost);
        if (room_id != null) {
            equipment.setRoom(roomService.getRoom(room_id));
        } else {
            Room room = new Room();
            room.setRoomId(0L);
            equipment.setRoom(new Room());
        }
        equipmentService.addEquipment(equipment);
        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        equipmentService.getEquipments(page - 1, size, sortBy).forEach(e -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(e)));
        model.addAttribute("equipments", equipmentsDTO);
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("location", "equipments");
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        model.addAttribute("countPages", Math.ceil(equipmentService.getCountRecords() * 1.0 / size));
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
        model.addAttribute("warehouse", roomService.getRoom(warehouseId));
        model.addAttribute("warehouseId", warehouseId);
        return "newequipment";
    }

    @RequestMapping(value = "/equipments", params = {"room_id"})
    public String getEquipments(Model model, @RequestParam(value = "room_id") Long roomId
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "equipmentId") String sortBy) {

        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        roomService.getEquipmentByRoomID(roomId).forEach(equipment -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(equipment)));
        model.addAttribute("equipments", equipmentsDTO);
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("location", "equipments");
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        model.addAttribute("countPages", Math.ceil(equipmentService.getCountRecords() * 1.0 / size));
        return "equipments";
    }


    @RequestMapping(value = "/equipments", params = {"id"})
    public String removeEquipment(Model model, @RequestParam(value = "id") Long equipmentId
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "equipmentId") String sortBy) {

        roomService.moveEquipments(roomService.getRoom(warehouseId), Collections.singletonList(equipmentId));
        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        equipmentService.getEquipments(page - 1, size, sortBy).forEach(equipment -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(equipment)));
        model.addAttribute("equipments", equipmentsDTO);
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("location", "equipments");
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        model.addAttribute("countPages", Math.ceil(equipmentService.getCountRecords() * 1.0 / size));
        return "equipments";
    }


    @RequestMapping(value = "/equipments")
    public String getEquipments(Model model
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "equipmentId") String sortBy) {

        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        equipmentService.getEquipments(page - 1, size, sortBy).forEach(equipment -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(equipment)));
        model.addAttribute("equipments", equipmentsDTO);
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("location", "equipments");
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        model.addAttribute("countPages", Math.ceil(equipmentService.getCountRecords() * 1.0 / size));
        return "equipments";
    }

    @RequestMapping(value = "/equipments", params = {"equipment_id", "room_id"}, method = RequestMethod.GET)
    public String getRooms(Model model, @RequestParam(value = "equipment_id") Long equipmentId
            , @RequestParam(value = "room_id") Long roomId
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "equipmentId") String sortBy) {

        equipmentService.removeEquipment(equipmentId);
        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        roomService.getEquipmentByRoomID(roomId).forEach(equipment -> equipmentsDTO.add(DTOUtil.createEquipmentDTO(equipment)));
        model.addAttribute("equipments", equipmentsDTO);
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("location", "equipments");
        model.addAttribute("countPages", Math.ceil(equipmentService.getCountRecords() * 1.0 / size));
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        return "equipments";
    }
}
