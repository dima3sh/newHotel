package org.azati.cources.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class AspectForAllMethods {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(org.azati.cources..*)")
    public void callAtMethods() { }

    @Before("callAtMethods()")
    public void beforeCallAtMethod1(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(a -> a.toString())
                .collect(Collectors.joining(","));
        logger.info("before " + jp.toString() + ", args=[" + args + "]");
    }

    @After("callAtMethods()")
    public void afterCallAt(JoinPoint jp) {
        logger.info("after " + jp.toString());
    }

    @AfterThrowing(value = "callAtMethods()", throwing = "exception")
    public void logAfterThrowing( Exception exception) {
        logger.error("Target Method resulted into exception, message {}", exception.getMessage());
    }
}

