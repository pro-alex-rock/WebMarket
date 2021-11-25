package webMarker.configuration;

import webMarker.service.ProductService;

public class ServiceFactory {
    private static ProductService productService;

    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    private ServiceFactory() {
        productService = new ProductService();
    }
}
