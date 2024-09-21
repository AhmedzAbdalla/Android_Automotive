package com.example.product_app_mvc;



import retrofit2.Call;
import retrofit2.http.GET;

// Interface for the Products API
public interface ProductService {
    // Get all products or apply a limit query if needed
    @GET("products")
    Call<ProductsResponse> getProducts();
}
