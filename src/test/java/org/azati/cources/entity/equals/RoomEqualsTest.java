package org.azati.cources.entity.equals;

import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Room;
import org.azati.cources.enums.StateEquipment;
import org.azati.cources.enums.StatusRoom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomEqualsTest {
    Room room1;
    Room room2;
    Equipment equipment11;
    Equipment equipment12;

    @Before
    public void init(){
        equipment11 = new Equipment("mobile", 1.3, 2,
                1, StateEquipment.NEW, 11);
        equipment12 = new Equipment("char", 10.2, 2,
                2, StateEquipment.NEW, 11);
        room1 = new Room(11, true, 2, 10, StatusRoom.CLEAN,
                new ArrayList<>(Arrays.asList(equipment11)));
        room2 = new Room(11, true, 2, 10, StatusRoom.CLEAN,
                new ArrayList<>(Arrays.asList(equipment12)));
    }

    @Test(expected = AssertionError.class)
    public void equalsDifferentEquipmentTest(){
        Assert.assertEquals(room1, room2);
    }

    @Test
    public void equalsRoom(){
        Assert.assertEquals(room1, room1);
    }

    @Test
    public void equalsSameRooms(){
        room2.setEquipments(new ArrayList<>(Arrays.asList(equipment11)));
        Assert.assertEquals(room1, room2);
    }
}
