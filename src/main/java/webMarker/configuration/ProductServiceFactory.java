package webMarker.configuration;

import webMarker.service.ProductService;

public class ProductServiceFactory {
    private static ProductService productService;

    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    private ProductServiceFactory() {
        productService = new ProductService();
    }
}
