package com.servlet;

import com.configuration.PageGenerator;
import com.configuration.ProductServiceFactory;
import com.model.Product;
import com.service.DefaultService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class EditServlet extends HttpServlet {
    private final DefaultService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", "");
        String url = req.getRequestURI();
        int id = getIdFromPath(url);
        Product product = (Product) productService.selectOne(id);
        pageVariables.put("product", product);

        resp.setContentType("text/html;charset=utf-8");
        String page = PageGenerator.instance().getPage("edit.ftl", pageVariables);
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
        product.setDescription(req.getParameter("description"));
        productService.updateOne(id, product);
        resp.sendRedirect("/products");
    }

    private int getIdFromPath(String url) {
        String id = url.substring(url.lastIndexOf('/') + 1);
        return Integer.parseInt(id);
    }
}
