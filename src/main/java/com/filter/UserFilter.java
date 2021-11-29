package com.filter;

import com.configuration.UserServiceFactory;
import com.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    private final UserService userService = UserServiceFactory.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.isExistUser(login, password)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            res.sendRedirect(req.getContextPath() + "/registration");
        }
    }

    @Override
    public void destroy() {

    }
}
