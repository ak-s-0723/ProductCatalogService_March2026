package org.example.productcatalogservice_march2026.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String  message) {
        super(message);
    }
}
