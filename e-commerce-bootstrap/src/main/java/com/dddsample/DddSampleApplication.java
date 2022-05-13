package com.dddsample;

import com.robustel.adapter.ddd.service.registry.spring.SpringServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableConfigurationProperties
@SpringBootApplication
@ComponentScan({"com.dddsample", "com.robustel"})
public class DddSampleApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DddSampleApplication.class);
        springApplication.addListeners(new SpringServiceRegistry());
        springApplication.run(args);
    }
}
