package org.azati.cources.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

public class ModelUtil {

    @Value("${warehouse.id}")
    private static Long warehouseId;

    public static void setStandardModelElements(Model model, Integer page, Integer size, String sortBy, Integer countPages, String location) {
        model.addAttribute("page", page);
        model.addAttribute("location", location);
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("countPages", countPages);
    }
}
