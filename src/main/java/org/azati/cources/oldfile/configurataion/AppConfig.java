package org.azati.cources.oldfile.configurataion;

import org.azati.cources.entity.Equipment;
import org.azati.cources.enums.StateEquipment;
import org.azati.cources.oldfile.repository.GuestRepository;
import org.azati.cources.oldfile.repository.RoomRepository;
import org.azati.cources.services.GuestService;
import org.azati.cources.services.RoomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.azati.cources")
public class AppConfig {
    @Bean
    public Equipment createEquipment() {
        return new Equipment("mobile", 0.75, 10, 2, StateEquipment.NEW, 3);
    }

    @Bean
    public GuestRepository guestRepository() {
        return new GuestRepository();
    }

    @Bean
    public GuestService guestService() {
        return new GuestService();
    }

    @Bean
    public RoomRepository roomRepository() {
        return new RoomRepository();
    }

    @Bean
    public RoomService roomService() {
        return new RoomService();
    }
}
