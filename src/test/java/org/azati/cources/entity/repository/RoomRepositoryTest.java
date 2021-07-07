package org.azati.cources.entity.repository;

import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Room;
import org.azati.cources.enums.StatusRoom;
import org.azati.cources.repository.RoomRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomRepositoryTest {
    RoomRepository repository;
    Room room;

    @Before
    public void init() {
        repository = new RoomRepository();
        Equipment equipment1 = new Equipment("TV", 10.2, 3, 1,
                org.azati.cources.enums.StateEquipment.NEW, 2);
        Equipment equipment2 = new Equipment("char", 10.2, 3, 1,
                org.azati.cources.enums.StateEquipment.NEW, 2);
        room = new Room(1, true, 10, 15, StatusRoom.CLEAN,
                new ArrayList<>(Arrays.asList(equipment1, equipment2)));
    }

    @Test
    public void addTest() {
        Assert.assertEquals(room, repository.addRoom(room));
    }

    @Test
    public void removeTest() {
        repository.addRoom(room);
        Assert.assertEquals(true, repository.removeRoom(room));
    }

    @Test
    public void removeBuNameTest() {
        repository.addRoom(room);
        Assert.assertEquals(true, repository.removeById(1));
    }

    @Test
    public void editGuestTest() {
        repository.addRoom(room);
        Assert.assertEquals(room, repository.editRoom(room));
    }

    @Test
    public void upCostTest() {
        repository.addRoom(room);
        repository.upCostRoom();
        Assert.assertEquals(room.getCostPerHour(), (Integer) 30);
    }
}
