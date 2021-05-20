package com.wei.hello.service;

import com.wei.hello.aop.GoodsJudgeAnno;
import com.wei.hello.dao.HelloWordMapping;
import com.wei.hello.service.api.HelloWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by weiwei on 2021/4/3.
 *
 * @author weiwei
 */
@Service
public class HelloWordServiceImpl implements HelloWordService {
    @Autowired
    private HelloWordMapping helloWordMapping;

    @Override
//    @GoodsJudgeAnno(judgeName = "测试")
    public String hello(String name) {
        String role = helloWordMapping.hello(name);
        return "你好" + role + name;
    }
}
