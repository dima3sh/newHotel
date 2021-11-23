package org.azati.cources.entity.repository;

import liquibase.pro.packaged.U;
import org.azati.cources.configuration.DataSourceConfig;
import org.azati.cources.dictionaries.UserRole;
import org.azati.cources.entity.AppUser;
import org.azati.cources.entity.Guest;
import org.azati.cources.entity.Room;
import org.azati.cources.enums.UserRoles;
import org.azati.cources.repository.GuestRepository;
import org.azati.cources.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { DataSourceConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class GuestRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void guestSaveTest(){
        AppUser user = new AppUser();
        user.setEmailAddress("example@gmail.com");
        user.setName("name");
        UserRole userRole = new UserRole();
        userRole.setUserRole(UserRoles.ASSISTANT);
        user.setUserRole(userRole);
        user.setUsername("lol");
        user.setPassword("password");
        userRepository.save(user);

        AppUser user2 = userRepository.findById(1L).get();
        Assert.assertEquals(user.getUsername(), user2.getUsername());
    }
}
