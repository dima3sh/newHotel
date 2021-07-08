package org.azati.cources.repository;

import org.azati.cources.entity.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Long> {

    public Guest findGuestByName(String name);
}
