package com.wei.demoTest;

import com.wei.myannotation.AutoInject;
import com.wei.myannotation.MyBean;

@MyBean
public class UserController {
    @AutoInject("userService")
    private IUserService userService;

    public User getUserByName(String name) {
        return userService.getUser(name);
    }
}
