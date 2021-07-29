package org.azati.cources.repository;

import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Long> {

    @Modifying
    @Query(value = "INSERT INTO equipment ( equipment_room_id, name_equipment, weight," +
            " cost_per_object, equipment_state_id, producer) VALUES" +
            " (:equipmentRoomId, :nameEquipment, :weight, :costPerObject," +
            " :equipmentStateId, :producer)", nativeQuery = true)
    @Transactional
    public void addEquipment(@Param("equipmentRoomId") Long equipmentRoomId,
                             @Param("nameEquipment") String nameEquipment, @Param("weight") Double weight,
                             @Param("costPerObject") Integer costPerObject
            , @Param("equipmentStateId") Integer equipmentStateId, @Param("producer") String producer);

    Page<Equipment> findAll(Pageable pageable);

    Page<Equipment> findAllByEquipmentRoomId(Room roomId, Pageable pageable);
}
