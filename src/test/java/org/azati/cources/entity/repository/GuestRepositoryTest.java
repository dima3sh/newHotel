package org.azati.cources.entity.repository;

import org.azati.cources.entity.Guest;
import org.azati.cources.repository.GuestRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class GuestRepositoryTest {
    GuestRepository repository;
    Guest guest;

    @Before
    public void init() {
        repository = new GuestRepository();
        LocalDateTime date = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        guest = new Guest("Rick", "23123", "12@gmail.com", 12, date, date);
    }

    @Test
    public void addTest() {
        Assert.assertEquals(guest, repository.addGuest(guest));
    }

    @Test
    public void removeTest() {
        repository.addGuest(guest);
        Assert.assertEquals(true, repository.removeGuest(guest));
    }

    @Test
    public void removeBuIndexTest() {
        repository.addGuest(guest);
        Assert.assertEquals(true, repository.removeGuest(0));
    }

    @Test
    public void removeBuNameTest() {
        repository.addGuest(guest);
        Assert.assertEquals(true, repository.removeByName("Rick"));
    }

    @Test
    public void editGuestTest() {
        repository.addGuest(guest);
        Assert.assertEquals(guest, repository.editGuest(guest, 0));
    }
}
