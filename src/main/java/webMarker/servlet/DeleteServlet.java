package webMarker.servlet;

import webMarker.configuration.ServiceFactory;
import webMarker.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    private final Service service = ServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        int id = getIdFromPath(url);
        service.delete(id);
        resp.sendRedirect(req.getContextPath() + "/products");
    }

    private int getIdFromPath(String url) {
        String id = url.substring(url.lastIndexOf('/') + 1);
        return Integer.parseInt(id);
    }
}
