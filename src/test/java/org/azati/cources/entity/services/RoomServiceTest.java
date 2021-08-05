package org.azati.cources.entity.services;

import org.azati.cources.entity.Room;
import org.azati.cources.enums.StatusRoom;
import org.azati.cources.services.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DataJpaTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    private Room room;

    @BeforeEach
    public void init() {
       // room = roomService.roomFactory(1L, true, 1, 12, StatusRoom.clean);
    }

    @Test
    public void addAndGetRoomTest() {
//        roomService.addRoom(room);
//        Room roomFromDB = roomService.getRoom(1L);
//        Assertions.assertEquals(roomFromDB, room);
//        roomService.removeRoom(roomFromDB.getRoomId());
    }


}
