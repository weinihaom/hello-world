package com.wei.core;

public class BeanInfo {
    private Class clasz;
    private String beanName;
    /**
     * 容器里的类型
     */
    private Class beanType;
    /**
     * 实例对象
     */
    private Object bean;
    /**
     * 代理对象
     */
    private Object proxyBean;

    public Class getClasz() {
        return clasz;
    }

    public void setClasz(Class clasz) {
        this.clasz = clasz;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class getBeanType() {
        return beanType;
    }

    public void setBeanType(Class beanType) {
        this.beanType = beanType;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Object getProxyBean() {
        return proxyBean;
    }

    public void setProxyBean(Object proxyBean) {
        this.proxyBean = proxyBean;
    }
}
