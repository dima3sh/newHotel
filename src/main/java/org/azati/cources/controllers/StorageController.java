package org.azati.cources.controllers;

import org.azati.cources.dto.EquipmentDTO;
import org.azati.cources.services.EquipmentService;
import org.azati.cources.services.RoomService;
import org.azati.cources.utils.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StorageController {

    @Autowired
    RoomService roomService;

    @Autowired
    EquipmentService equipmentService;

    @Value("${warehouse.id}")
    private Long warehouseId;

    @RequestMapping(value = "/storage")
    public String getStorage(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "equipmentId") String sortBy) {

        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        equipmentService.getEquipments(page - 1, size, sortBy).forEach(equipment -> {
            if (warehouseId == equipment.getRoom().getRoomId()) {
                equipmentDTOList.add(DTOUtil.createEquipmentDTO(equipment));
            }
        });
        model.addAttribute("location", "storage");
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        model.addAttribute("equipments", equipmentDTOList);
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("countPages", (int)(Math.ceil(equipmentService.getCountRecords() * 1.0 / size)));
        return "storage";
    }

    @RequestMapping(value = "/storage", params = {"id"}, method = RequestMethod.GET)
    public String deleteElemFromStorage(Model model, @RequestParam("id") Long id
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "equipmentId") String sortBy) {
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        equipmentService.removeEquipment(id);
        roomService.getRoom(warehouseId).getEquipments().forEach(equipment -> {
            if (equipment.getEquipment_id() != id) {
                equipmentDTOList.add(DTOUtil.createEquipmentDTO(equipment));
            }
        });
        model.addAttribute("page", page);
        model.addAttribute("location", "storage");
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        model.addAttribute("equipments", equipmentDTOList);
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("countPages", Math.ceil(equipmentService.getCountRecords() * 1.0 / size));
        return "storage";
    }

}
