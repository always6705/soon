package com.lucky.soon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SoonApplication {

    private static final Logger logger = LoggerFactory.getLogger(SoonApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SoonApplication.class, args);

        logger.info("==========================项目已启动==========================");
    }

}
