package com.wei.hello.dao;


import org.springframework.stereotype.Repository;

/**
 * Created by weiwei on 2021/4/3.
 * @author weiwei
 */
@Repository
public interface HelloWordMapping {
    /**
     * hello
     *
     * @param id
     * @return
     */
    String hello(String id);
}
