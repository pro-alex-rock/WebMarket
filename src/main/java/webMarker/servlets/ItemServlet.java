package webMarker.servlets;

import webMarker.dao.DaoResource;
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

public class ItemServlet extends HttpServlet {
    private final DataSource dataSource = new PostgresSource();
    private final DaoResource productDao = ProductDaoFactory.getInstance(dataSource);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(req);
        pageVariables.put("message", "");
        Product product = productDao.selectOne(Integer.parseInt(req.getParameter("id")));
        pageVariables.put("id", product.getId());
        pageVariables.put("name", product.getName());
        pageVariables.put("price", product.getPrice());

        resp.setContentType("text/html;charset=utf-8");
        String page = PageGenerator.instance().getPage("product.html", pageVariables);
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
        productDao.update(id, product);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productDao.delete(id);
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
