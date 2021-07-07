package org.azati.cources;

import org.azati.cources.oldfile.configurataion.AppConfig;
import org.azati.cources.services.GuestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        GuestService service = (GuestService) applicationContext.getBean("guestService");
        service.remove(0);
    }
}
