package com.wei.demoTest;

import com.wei.myannotation.MyBean;

@MyBean("service")
public class UserService implements IUserService{

    public User getUser(String name) {
        User user = new User();
        user.setAge(10);
        user.setName(name);
        return user;
    }
}
