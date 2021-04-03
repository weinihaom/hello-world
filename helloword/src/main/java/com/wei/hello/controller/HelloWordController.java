package com.wei.hello.controller;

import com.wei.hello.service.api.HelloWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Created by weiwei on 2021/4/3.
 *
 * @author weiwei
 */

@RestController
public class HelloWordController {
    private static Logger logger = LoggerFactory.getLogger(HelloWordController.class);

    @Autowired
    private HelloWordService helloWordService;

    @RequestMapping("/hello/{name}")
    public String helloword(@PathVariable("name") String name) {
        logger.info(name);
        if (StringUtils.isEmpty(name)) {
            return "名字不能为空！";
        }
        return helloWordService.hello(name);
    }
}
