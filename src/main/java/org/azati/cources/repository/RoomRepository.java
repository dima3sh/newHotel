package org.azati.cources.repository;

import org.azati.cources.entity.Room;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {

    @Modifying
    @Query(value = " into room (room_id, is_free_room, number_of_beds, cost_per_hour, status_room)" +
            "values ")
    public Room addRoom(Room room);
}
