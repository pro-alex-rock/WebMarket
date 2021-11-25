package webMarker.configuration;

import webMarker.service.DefaultService;
import webMarker.service.ProductService;

public class ProductServiceFactory {
    private static DefaultService productService;

    public static DefaultService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    private ProductServiceFactory() {
        productService = new ProductService();
    }
}
