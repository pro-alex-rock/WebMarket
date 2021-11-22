package webMarker;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webMarker.servlet.*;

public class Main {
        private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new HomeServlet()), "/");
        context.addServlet(new ServletHolder(new ProductsServlet()), "/products");
        context.addServlet(new ServletHolder(new EditServlet()), "/product");
        context.addServlet(new ServletHolder(new AddServlet()), "/products/add");
        context.addServlet(new ServletHolder(new EditServlet()), "/products/edit/*");
        context.addServlet(new ServletHolder(new DeleteServlet()), "/products/delete/*");
        context.addServlet(new ServletHolder(new LoginServlet()), "/login");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
