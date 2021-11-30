package com.filter;

import com.configuration.UserServiceFactory;
import com.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    private final UserService userService = UserServiceFactory.getInstance();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean isAuth = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equalsIgnoreCase("user-token")) {
                    isAuth = true;
                }
            }
        }

        if (isAuth) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/login");
        }

       /* String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (userService.isExistUser(login, password)) {
            response.sendRedirect("/products");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + "/registration");
        }*/
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
    }
}
