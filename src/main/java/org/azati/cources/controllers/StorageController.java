package org.azati.cources.controllers;

import org.azati.cources.dto.EquipmentDTO;
import org.azati.cources.services.EquipmentService;
import org.azati.cources.services.RoomService;
import org.azati.cources.utils.DTOUtil;
import org.azati.cources.utils.ModelUtil;
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

    @RequestMapping(value = "/storage", method = RequestMethod.GET)
    public String deleteElemFromStorage(Model model, @RequestParam(value = "id", defaultValue = "") Long id
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "equipmentId") String sortBy) {
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        if (id != null) {
            equipmentService.removeEquipment(id);
        }

        equipmentService.getEquipmentsByRoomID(warehouseId, page - 1, size, sortBy)
                .forEach(equipment -> equipmentDTOList.add(DTOUtil.createEquipmentDTO(equipment)));

        ModelUtil.setStandardModelElements(model, page, size, sortBy, (int) (Math.ceil(equipmentDTOList.size() * 1.0 / size)), "storage");
        model.addAttribute("equipments", equipmentDTOList);
        return "storage";
    }

}
