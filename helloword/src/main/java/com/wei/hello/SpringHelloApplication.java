package com.wei.hello;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by weiwei on 2021/4/2.
 *
 * @author weiwei
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
@MapperScan("com.wei.hello.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringHelloApplication {
    public static Logger logger = LoggerFactory.getLogger(SpringHelloApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringHelloApplication.class, args);
        logger.info("hello word is start!");
    }
}
