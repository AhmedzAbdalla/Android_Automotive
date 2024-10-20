package com.example.myworkmanager

import com.example.myworkmanager.ProductResponse

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    fun fetchProducts(): Call<ProductResponse>
    @GET("products")
    suspend fun fetchProducts2(): ProductResponse

    @GET("products")
    suspend fun fetchProducts3(): Response<ProductResponse>
}
