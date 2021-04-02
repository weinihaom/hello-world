package com.wei.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

/**
 * Created by weiwei on 2021/4/2.
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
    public static Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
        logger.info("hello word is start!");
    }
}
