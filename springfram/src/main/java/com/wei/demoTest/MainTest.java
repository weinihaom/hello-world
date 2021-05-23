package com.wei.demoTest;

import com.wei.core.BeanFactory;

public class MainTest {
    public static void main(String[] args) {
        iocFramStart();
    }

    private static void test() {
        String defaultName = "MMM";
        System.out.println(defaultName);
        String dn = defaultName.substring(0,1);
        System.out.println(dn);
        String d= dn.toLowerCase();
        System.out.println(d);
        defaultName= defaultName.replaceFirst(dn,d);
        System.out.println(defaultName);
    }


    private static void iocFramStart() {
        String packge = "com.wei.demoTest";
        BeanFactory beanFactory = new BeanFactory(packge);
        UserController userController = (UserController) beanFactory.getContext().getBean(UserController.class);
        User user = userController.getUserByName("ww");
        System.out.println(user.getAge());
    }
}
