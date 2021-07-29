package org.azati.cources.repository;

import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

    @Modifying
    @Query(value = "INSERT INTO room ( is_free_room, number_of_beds, cost_per_hour, status_room_id) VALUES" +
            " (:isFreeRoom, :numberOfBeds, :costPerHour, :statusId)", nativeQuery = true)
    @Transactional
    public void addRoom( @Param("isFreeRoom") Boolean isFreeRoom,
                        @Param("numberOfBeds") Integer numberOfBeds, @Param("costPerHour") Integer costPerHour,
                        @Param("statusId") Integer statusId);

    public List<Room> findAllByIsFreeRoom (Boolean flag);

    Page<Room> findAll(Pageable pageable);
}
