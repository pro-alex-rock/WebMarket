package webMarker.configuration;

import webMarker.service.UserService;

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
