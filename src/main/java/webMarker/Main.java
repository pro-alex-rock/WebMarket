package webMarker;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import webMarker.servlets.AllRequestsServlet;
import webMarker.servlets.ItemServlet;
import webMarker.servlets.ProductsServlet;

public class Main {
    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new AllRequestsServlet()), "/*");
        context.addServlet(new ServletHolder(new ProductsServlet()), "/products");
        context.addServlet(new ServletHolder(new ItemServlet()), "/product");
        context.addServlet(new ServletHolder(new ItemServlet()), "/products/edit");
        context.addServlet(new ServletHolder(new ItemServlet()), "/products/delete");

        Server server = new Server(8090);
        server.setHandler(context);

        server.start();
        /*DataSource dataSource = new PostgresSource();
        DataResource productDao = ProductDaoFactory.getInstance(dataSource);
        System.out.println(productDao.selectAll());*/
    }
}
