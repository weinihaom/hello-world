package com.wei.demoTest;

import com.wei.core.BeanFactory;

public class MainTest {
    public static void main(String[] args) {
        String packge = "com.wei.demoTest";
        BeanFactory beanFactory = new BeanFactory(packge);
        UserController userController = (UserController) beanFactory.getContext().getBean(UserController.class);
        User user = userController.getUserByName("ww");
        System.out.println(user.getAge());
    }
}
