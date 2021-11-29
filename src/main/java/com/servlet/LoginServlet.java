package com.servlet;

import com.configuration.PageGenerator;
import com.configuration.UserServiceFactory;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private final UserService userService = UserServiceFactory.getInstance();

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

        String token = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("user-token", token);
        resp.addCookie(cookie);

        if (userService.isExistUser(login, password)) {
            resp.sendRedirect("/products");
        } else {
            resp.sendRedirect("/registration");
        }
    }
}