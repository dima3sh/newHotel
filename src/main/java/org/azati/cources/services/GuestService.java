package org.azati.cources.services;

import org.azati.cources.entity.Guest;
import org.azati.cources.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    public Guest addGuest(Guest guest) {
        guestRepository.save(guest);
        return guest;
    }

    public Optional<Guest> getGuest(Long guest_id) {
        return guestRepository.findById(guest_id);
    }

    public List<Guest> getGuests(String name) {
        return guestRepository.findGuestsByName(name);
    }

    public Integer removeGuest(String phoneNumber){
        return guestRepository.deleteGuestByPhoneNumber(phoneNumber);
    }
}
