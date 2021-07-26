package org.azati.cources.repository;

import org.azati.cources.entity.Equipment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

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

}
