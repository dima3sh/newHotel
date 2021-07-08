package org.azati.cources.repository;

import org.azati.cources.entity.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GuestRepository extends CrudRepository<Guest, Long> {

    public List<Guest> findGuestsByName(String name);

    @Transactional
    public Integer deleteGuestByPhoneNumber(String phone);

    public List<Guest> findGuestByInvoice (Integer invoice);
}
