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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {
    private final Service service = ServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isAuth = false;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    isAuth = true;
                }
            }
        }
        if (isAuth) {
            Map<String, Object> pageVariables = createPageVariablesMap(req);
            pageVariables.put("message", "");
            List<Product> products = service.selectAll();
            pageVariables.put("products", products);

            resp.setContentType("text/html;charset=utf-8");
            String page = PageGenerator.instance().getPage("products.ftl", pageVariables);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(page);
        } else {
            resp.sendRedirect("/login");
        }
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
