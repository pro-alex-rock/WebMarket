package webMarker.servlets;

import webMarker.dao.DataResource;
import webMarker.dao.DataSource;
import webMarker.dao.PostgresSource;
import webMarker.model.Product;
import webMarker.service.PageGenerator;
import webMarker.service.ProductDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AddServlet extends HttpServlet {
    private final DataSource dataSource = new PostgresSource();
    private final DataResource productDao = ProductDaoFactory.getInstance(dataSource);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.sendRedirect(req.getContextPath() + "/products/add");

        Map<String, Object> pageVariables = createPageVariablesMap(req);
        String message = req.getParameter("message");
        pageVariables.put("message", message == null ? "" : message);
        resp.setContentType("text/html;charset=utf-8");
        String page = PageGenerator.instance().getPage("add.html", pageVariables);
        resp.getWriter().println(page);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        int id = Integer.parseInt(req.getParameter("id"));
        product.setId(id);
        product.setName(req.getParameter("name"));
        product.setPrice(new BigDecimal(req.getParameter("price")));
        productDao.create(product);
        Map<String, Object> pageVariables = createPageVariablesMap(req);
        String message = req.getParameter("message");
        pageVariables.put("message", message == null ? "" : message);
        resp.setContentType("text/html;charset=utf-8");
        String page = PageGenerator.instance().getPage("add.html", pageVariables);
        resp.getWriter().println(page);
        resp.setStatus(HttpServletResponse.SC_OK);
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
