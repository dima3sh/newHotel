package org.azati.cources.services;

import org.azati.cources.entity.Guest;
import org.azati.cources.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    public Guest addGuest(Guest guest) {
        guestRepository.save(guest);
        return guest;
    }
}
