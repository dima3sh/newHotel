package org.azati.cources.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ModelUtil {

    public static void setStandardModelElements(Model model, Integer page, Integer size, String sortBy, Integer countPages, String location) {
        model.addAttribute("page", page);
        model.addAttribute("location", location);
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        model.addAttribute("warehouseId", Integer.parseInt(getProperties().getProperty("warehouse.id")));
        model.addAttribute("countPages", countPages);
    }

    private static Properties getProperties() {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
        } catch (IOException e) {

        }
        return property;
    }
}
