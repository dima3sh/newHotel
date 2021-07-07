package org.azati.cources.oldfile.repository;

import org.azati.cources.entity.Guest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestRepository {
    private List<Guest> guestRepository = new ArrayList<>();

    public Guest addGuest(Guest guest) {
        guestRepository.add(guest);
        return guest;
    }

    public Boolean removeGuest(Guest guest) {
        return guestRepository.remove(guest);
    }

    public Boolean removeGuest(Integer index) {
        guestRepository.remove((int)index);
        return true;
    }

    public Guest editGuest(Guest guest, Integer index) {
        guest = guestRepository.set(index, guest);
        return guest;
    }

    public Boolean removeByName(String name) {
        final boolean[] flag = {false};
        ArrayList<Guest> repositoryCopy = new ArrayList<>(guestRepository);

        repositoryCopy.forEach(guest -> {
            if (guest.getName().equals(name)) {
                guestRepository.remove(guest);
                flag[0] = true;
            }
        });

        return flag[0];
    }
}
