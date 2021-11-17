package webMarker.servlets;

import webMarker.dao.DataResource;
import webMarker.model.Product;
import webMarker.service.PageGenerator;
import webMarker.service.ProductDaoGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OneProductServlet extends HttpServlet {
    private final DataResource productDao = ProductDaoGenerator.getInstance();
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
