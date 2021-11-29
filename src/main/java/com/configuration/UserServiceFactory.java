package com.configuration;

import com.service.UserService;

public class UserServiceFactory {
    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserServiceFactory() {
        userService = new UserService();
    }
}
