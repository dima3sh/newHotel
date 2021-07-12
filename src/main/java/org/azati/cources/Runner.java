package org.azati.cources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJms
public class Runner extends SpringBootServletInitializer {

    public static Logger log = LoggerFactory.getLogger(Runner.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Runner.class);
    }

    public static void main(String[] args) {
        log.info("Start application");
        SpringApplication.run(Runner.class, args);
    }
}
