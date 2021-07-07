package org.azati.cources.entity.services;

import org.azati.cources.configuration.AppConfig;
import org.azati.cources.entity.Guest;
import org.azati.cources.repository.GuestRepository;
import org.azati.cources.services.GuestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.time.Month;

public class GuestServicesTest {
    GuestService guestService;
    Guest guest;
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    @Before
    public void init() {
        guestService = new GuestService(ctx.getBean(GuestRepository.class));
        LocalDateTime date = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        guest = new Guest("Rick", "23123", "12@gmail.com", 12, date, date);
    }

    @Test
    public void addTest() {
        Assert.assertEquals(guest, guestService.add(guest));
    }

    @Test
    public void removeTest() {
        guestService.add(guest);
        Assert.assertEquals(true, guestService.remove(guest));
    }

    @Test
    public void removeBuIndexTest() {
        guestService.add(guest);
        Assert.assertEquals(true, guestService.remove(0));
    }

    @Test
    public void removeBuNameTest() {
        guestService.add(guest);
        Assert.assertEquals(true, guestService.removeByName("Rick"));
    }

    @Test
    public void editGuestTest() {
        guestService.add(guest);
        Assert.assertEquals(guest, guestService.edit(guest, 0));
    }

}
