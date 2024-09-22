package com.example.product_app_mvc.model;

import androidx.annotation.NonNull;

import java.util.List;

// Wrapper class for the response from the API
public class ProductsResponse {
    private List<POJO_class> products;

    public ProductsResponse(List<POJO_class> POJOS) {
        this.products = POJOS;
    }

    public List<POJO_class> getProducts() {
        return products;
    }

    @NonNull
    @Override
    public String toString() {
        return "ProductsResponse{" +
                "products=" + products +
                '}';
    }

}
