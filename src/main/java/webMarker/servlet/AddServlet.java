package webMarker.servlet;

import webMarker.configuration.PageGenerator;
import webMarker.configuration.ProductServiceFactory;
import webMarker.model.Product;
import webMarker.service.DefaultService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class AddServlet extends HttpServlet {
    private final DefaultService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String page = PageGenerator.instance().getPage("add.ftl");
        resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(new BigDecimal(req.getParameter("price")));
        product.setDescription(req.getParameter("description"));
        productService.create(product);
        resp.sendRedirect("/products");
    }
}
