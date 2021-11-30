package com.servlet;

import com.configuration.PageGenerator;
import com.configuration.UserServiceFactory;
import com.model.User;
import com.service.SecurityService;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private final UserService userService = UserServiceFactory.getInstance();
    private final SecurityService securityService = new SecurityService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String page = PageGenerator.instance().getPage("registration.ftl");
        resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
            String sole = securityService.getUUID();
            String passwordEncoded = securityService.getPasswordEncode(password + sole);

            User user = new User();
            user.setName(login);
            user.setPassword(passwordEncoded);
            user.setSole(sole);

            userService.create(user);
            resp.sendRedirect("/products");
    }
}
