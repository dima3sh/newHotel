package org.azati.cources.entity;

import org.azati.cources.configuration.AppConfig;
import org.azati.cources.enums.StateEquipment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EquipmentTest {
    Equipment equipment;
    ApplicationContext ctx;
    ApplicationContext ctx2;
    Equipment equipment2;

    @Before
    public void init() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        equipment = ctx.getBean(Equipment.class);
        equipment.setStateEquipment(StateEquipment.NEW);
        ctx2 = new AnnotationConfigApplicationContext(AppConfig.class);
        equipment2 = ctx2.getBean(Equipment.class);
    }

    @Test
    public void getStateEquipmentTest() {
        Assert.assertEquals(equipment.getStateEquipment(), StateEquipment.NEW);
    }

    @Test
    public void getRoom_idTest(){
        Assert.assertEquals((int)equipment2.getRoom_id(),3);
    }

}
