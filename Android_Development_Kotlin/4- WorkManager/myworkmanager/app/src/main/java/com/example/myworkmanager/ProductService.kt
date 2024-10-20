package com.example.myworkmanager

import ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    fun fetchProducts(): Call<ProductResponse>
}
