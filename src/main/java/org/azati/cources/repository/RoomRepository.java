package org.azati.cources.repository;

import org.azati.cources.entity.Room;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoomRepository extends CrudRepository<Room, Long> {

    @Modifying
    @Query(value = "INSERT INTO room (room_id, is_free_room, number_of_beds, cost_per_hour, status_room_id) VALUES" +
            " (:roomId, :isFreeRoom, :numberOfBeds, :costPerHour, :statusId)", nativeQuery = true)
    @Transactional
    public void addRoom(@Param("roomId") Long roomId, @Param("isFreeRoom") Boolean isFreeRoom,
                        @Param("numberOfBeds") Integer numberOfBeds, @Param("costPerHour") Integer costPerHour,
                        @Param("statusId") Integer statusId);

}
