package webMarker.servlet;

import webMarker.configuration.PageGenerator;
import webMarker.configuration.ProductServiceFactory;
import webMarker.configuration.UserServiceFactory;
import webMarker.service.ProductService;
import webMarker.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String message = req.getParameter("message");
        pageVariables.put("message", message == null ? "" : message);
        resp.setContentType("text/html;charset=utf-8");
        String page = PageGenerator.instance().getPage("login.ftl", pageVariables);
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
