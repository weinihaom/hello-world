package com.wei.core;

import com.wei.myannotation.AutoInject;
import com.wei.myannotation.MyBean;
import com.wei.util.ClassUtil;
import com.wei.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanFactory {
    private String basePackage;
    private Context context = new Context();

    public BeanFactory(String basePackage) {
        this.basePackage = basePackage;
        init();
    }

    private void init() {
        List<BeanInfo> myBeanList = scanPackageAndLoadClass(basePackage);
        try {
            injectBeans(myBeanList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private List<BeanInfo> scanPackageAndLoadClass(String basePackage) {
        List<BeanInfo> myBeanList = new ArrayList<BeanInfo>();
        Set<String> classNames = ClassUtil.getClassNames(basePackage);
        for (String className: classNames) {
            try {
                Class clasz = Class.forName(className);
                if (clasz.isAnnotationPresent(MyBean.class)) {
                    MyBean myBeanAnnotation = (MyBean) clasz.getAnnotation(MyBean.class);
                    //获取注解的bean的名称
                    String beanName = myBeanAnnotation.value();
                    Class[] interfaces = clasz.getInterfaces();
                    boolean isProxyBean = interfaces != null && interfaces.length > 0;
                    Class type = getBeanType(clasz, isProxyBean);
                    Object bean = clasz.newInstance();
                    Object iocBean = bean;
                    if (isProxyBean) {
                        Object proxyBean = this.createBeanProxy(bean);
                        iocBean = proxyBean;
                        context.putBean(type, iocBean);
                    }else{
                        context.putBean(clasz, bean);
                    }
                    //可以修改，增加默认bean的名称
                    if (StringUtils.isNotBlank(beanName)) {
                        context.putBean(beanName, iocBean);
                    }else {
                       String defaultName= clasz.getSimpleName();
                       context.putBean(StringUtils.replaceFirstCase(defaultName), iocBean);
                    }
                    BeanInfo beanInfo = new BeanInfo();
                    beanInfo.setClasz(clasz);
                    beanInfo.setBeanName(beanName);
                    beanInfo.setBeanType(type);
                    beanInfo.setBean(bean);
                    beanInfo.setProxyBean(isProxyBean ? iocBean : null);
                    myBeanList.add(beanInfo);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return myBeanList;
    }

    private void injectBeans(List<BeanInfo> myBeanList) throws IllegalAccessException {
        for (BeanInfo beanInfo : myBeanList) {
            Class beanClass = beanInfo.getClasz();
            Object bean = beanInfo.getBean();
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields) {
                //判断是否有注解，需要注入对象
                if (field.isAnnotationPresent(AutoInject.class)) {
                    AutoInject autoInject = field.getAnnotation(AutoInject.class);
                    String injectBeanName = autoInject.value();
                    Class injectClasz = field.getType();
                    Object proxyBean = null;
                    if (StringUtils.isNotBlank(injectBeanName)) {
                        proxyBean = context.getBean(injectBeanName);
                    } else {
                        proxyBean = context.getBean(injectClasz);
                    }
                    field.setAccessible(true);
                    field.set(bean, proxyBean);
                }
            }
        }
    }

    private Object createBeanProxy(Object bean) {
        InvocationHandler invocationHandler = new BeanProxy(bean);
        Object proxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
                invocationHandler);
        return proxy;
    }

    private Class getBeanType(Class clasz, boolean isProxybean) {
        Class beanType = null;
        if (isProxybean) {
            beanType = clasz.getInterfaces()[0];
        } else {
            beanType = clasz;
        }
        return beanType;
    }

    public Context getContext() {
        return context;
    }
}
