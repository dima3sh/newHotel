package org.azati.cources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.EnableJms;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJms
@EnableSwagger2
public class Runner extends SpringBootServletInitializer {

    public static Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        log.info("Start application");
        SpringApplication.run(Runner.class, args);
    }


}
