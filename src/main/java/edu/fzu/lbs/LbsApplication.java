package edu.fzu.lbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot启动类
 */
@EnableScheduling
@SpringBootApplication
public class LbsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LbsApplication.class, args);
    }
}
