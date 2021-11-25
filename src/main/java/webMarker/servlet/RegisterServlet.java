package webMarker.servlet;

import webMarker.configuration.UserServiceFactory;
import webMarker.model.User;
import webMarker.service.SecurityService;
import webMarker.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean isExistUser = userService.isExistUser(login, password);
        if (isExistUser) {
            resp.sendRedirect("/login");
        } else {
            User user = new User();
            user.setName(login);
            user.setPassword(new SecurityService().getPasswordEncode(login, password));
            userService.create(user);
            resp.sendRedirect("/products");
        }
    }
}
