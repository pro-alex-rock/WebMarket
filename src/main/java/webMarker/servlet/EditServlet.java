package webMarker.servlet;

import webMarker.configuration.PageGenerator;
import webMarker.configuration.ServiceFactory;
import webMarker.model.Product;
import webMarker.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class EditServlet extends HttpServlet {
    private final Service service = ServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(req);
        pageVariables.put("message", "");
        String url = req.getRequestURI();
        int id = getIdFromPath(url);
        Product product = service.selectOne(id);
        pageVariables.put("product", product);

        resp.setContentType("text/html;charset=utf-8");
        String page = PageGenerator.instance().getPage("edit.ftl", pageVariables);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        String url = req.getRequestURI();
        int id = getIdFromPath(url);
        product.setId(id);
        product.setName(req.getParameter("name"));
        product.setPrice(new BigDecimal(req.getParameter("price")));
        service.updateOne(id, product);
        resp.sendRedirect(req.getContextPath() + "/products");
    }

    private int getIdFromPath(String url) {
        String id = url.substring(url.lastIndexOf('/') + 1);
        return Integer.parseInt(id);
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
