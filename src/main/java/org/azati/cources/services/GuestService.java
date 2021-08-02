package org.azati.cources.services;

import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Guest;
import org.azati.cources.entity.Room;
import org.azati.cources.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    public Guest addGuest(Guest guest) {
        guestRepository.addGuest(guest.getGuestRoomId().getRoomId(), guest.getName(), guest.getPhoneNumber(),
                guest.getEmailAddress(), guest.getInvoice(), guest.getDepartureTime(), guest.getArrivalTime());
        return guest;
    }

    public Guest getGuest(Long guest_id) {
        return guestRepository.findById(guest_id).get();
    }

    public List<Guest> getGuests(String name) {
        return guestRepository.findGuestsByName(name);
    }

    public Integer removeGuest(String phoneNumber) {
        return guestRepository.deleteGuestByPhoneNumber(phoneNumber);
    }

    public void updateInvoice() {
        List<Guest> guests = (ArrayList<Guest>) guestRepository.findAll();
        guests = guests.stream()
                .peek(guest -> guest.setInvoice(guest.getInvoice() + guest.getGuestRoomId().getCostPerHour()))
                .collect(Collectors.toList());
        guestRepository.saveAll(guests);
    }

    public List<Guest> getGuestNeedFree(Integer hoursMax, Integer hoursMin) {
        List<Guest> guests;
        Duration duration1 = Duration.ofHours(hoursMax);
        Duration duration2 = Duration.ofHours(hoursMin);

        guests = ((ArrayList<Guest>) guestRepository.findAll()).stream().filter(guest ->
        {
            Duration duration3 = Duration.between(LocalDateTime.now(), guest.getArrivalTime());
            return (duration3.getSeconds() < 0 || (duration1.compareTo(duration3) > 0 && duration3.compareTo(duration2) > 0));
        }).collect(Collectors.toList());
        return guests;
    }

    public Guest updateGuest(Guest guest) {
        Guest upGuest = guestRepository.findById(guest.getGuestId()).get();
        upGuest.setGuestRoomId(guest.getGuestRoomId());
        upGuest.setArrivalTime(guest.getArrivalTime());
        upGuest.setDepartureTime(guest.getDepartureTime());
        upGuest.setEmailAddress(guest.getEmailAddress());
        upGuest.setPhoneNumber(guest.getPhoneNumber());
        upGuest.setName(guest.getName());
        guestRepository.save(upGuest);
        return guest;
    }

    public List<Guest> getGuests() {
        return (ArrayList<Guest>) guestRepository.findAll();
    }

    public void removeGuest(Long guestId) {
        guestRepository.deleteById(guestId);
    }

    public Guest guestFactory(String name, String email, String phone, Long roomId, LocalDateTime timeOut
            , LocalDateTime timeIn, Integer invoice) {
        Guest newGuest = new Guest();
        newGuest.setName(name);
        newGuest.setEmailAddress(email);
        newGuest.setPhoneNumber(phone);
        Room room = new Room();
        room.setRoomId(roomId);
        newGuest.setGuestRoomId(room);
        newGuest.setDepartureTime(timeIn);
        newGuest.setArrivalTime(timeOut);
        newGuest.setInvoice(invoice);
        return newGuest;
    }

    public List<Guest> getGuests(Integer numberPage, Integer countRecord, String sortBy) {
        Pageable elements = PageRequest.of(numberPage, countRecord, Sort.by(sortBy));
        Page<Guest> allGuests = guestRepository.findAll(elements);
        return allGuests.getContent();
    }

    public Long getCountRecords() {
        return guestRepository.count();
    }

}
