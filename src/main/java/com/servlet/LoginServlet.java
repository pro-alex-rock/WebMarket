package com.servlet;

import com.configuration.PageGenerator;
import com.configuration.UserServiceFactory;
import com.model.MyProperties;
import com.model.User;
import com.service.SecurityService;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    private final UserService userService = UserServiceFactory.getInstance();
    private final SecurityService securityService = new SecurityService();
    private final MyProperties properties = new MyProperties();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String page = PageGenerator.instance().getPage("login.ftl");
        resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> userOptional = userService.getByName(login);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String passwordEncode = securityService.getPasswordEncode(password + user.getSole());
            if (Objects.equals(passwordEncode, user.getPassword())) {
                Cookie cookie = new Cookie("user-token", user.getSole());
                cookie.setMaxAge(Integer.parseInt(properties.getProperty("cookie.expiration.date")));
                resp.addCookie(cookie);
                resp.sendRedirect("/products");
            }
        } else {
            resp.sendRedirect("/registration");
        }
    }
}
