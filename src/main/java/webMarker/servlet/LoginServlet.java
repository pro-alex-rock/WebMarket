package webMarker.servlet;

import webMarker.configuration.PageGenerator;
import webMarker.configuration.ServiceFactory;
import webMarker.model.Product;
import webMarker.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private final Service service = ServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(req);
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
        System.out.println(login + " : " + password);

        String token = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("user-token", token);
        resp.addCookie(cookie);

        /*Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(new BigDecimal(req.getParameter("price")));
        service.create(product);
        resp.sendRedirect(req.getContextPath() + "/products");*/
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
