package com.configuration;

import com.service.DefaultService;
import com.service.ProductService;

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
