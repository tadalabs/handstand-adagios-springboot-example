package org.handstand.spring.demo;

import org.handstand.spring.demo.entity.SessionEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {
    public static Map<String, SessionEntity> validSessions = new HashMap<String, SessionEntity>();
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
