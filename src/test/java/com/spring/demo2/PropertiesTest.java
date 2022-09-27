package com.spring.demo2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(properties = {"wee.age=48"})
public class PropertiesTest {

    @Autowired
    Environment environment;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testMethod(){
    log.info("server.port : "+environment.getProperty("server.port"));
    log.info("wee.name = "+environment.getProperty("wee.age"));
    }
}
