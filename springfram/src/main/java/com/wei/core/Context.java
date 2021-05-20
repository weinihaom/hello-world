package com.wei.core;

import java.util.HashMap;
import java.util.Map;

public class Context {
    /**
     * 根据bean名称存储bean的map对象
     */
    private Map<String, Object> nameBeanMap = new HashMap<String, Object>();
    private Map<Class, Object> typeBeanMap = new HashMap();

    public Object getBean(String name) {
        return nameBeanMap.get(name);
    }

    public Object getBean(Class clasz) {
        return typeBeanMap.get(clasz);
    }

    public Object putBean(String name, Object bean) {
        return nameBeanMap.put(name, bean);
    }

    public Object putBean(Class clasz, Object bean) {
        return typeBeanMap.put(clasz, bean);
    }
}
