package org.azati.cources.entity.services;

import org.azati.cources.configuration.AppConfig;
import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Room;
import org.azati.cources.enums.StatusRoom;
import org.azati.cources.repository.RoomRepository;
import org.azati.cources.services.RoomService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomServiceTest {
    RoomService roomService;
    Room room;
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    @Before
    public void init() {
        roomService = new RoomService(ctx.getBean(RoomRepository.class));
        Equipment equipment1 = new Equipment("TV", 10.2, 3, 1,
                org.azati.cources.enums.StateEquipment.NEW, 2);
        Equipment equipment2 = new Equipment("char", 10.2, 3, 1,
                org.azati.cources.enums.StateEquipment.NEW, 2);
        room = new Room(1, true, 10, 15, StatusRoom.CLEAN,
                new ArrayList<>(Arrays.asList(equipment1, equipment2)));
    }

    @Test()
    public void addTest() {
        Assert.assertEquals(room, roomService.add(room));
    }

    @Test
    public void removeTest() {
        roomService.add(room);
        Assert.assertEquals(true, roomService.remove(room));
    }

    @Test
    public void removeBuIdTest() {
        roomService.add(room);
        Assert.assertEquals(true, roomService.removeById(1));
    }

    @Test
    public void editRoomTest() {
        roomService.add(room);
        Assert.assertEquals(room, roomService.edit(room));
    }

    @Test
    public void upCostTest() {
        roomService.add(room);
        roomService.upCostRoom();
        Assert.assertEquals(room.getCostPerHour(), (Integer) 30);
    }
}
