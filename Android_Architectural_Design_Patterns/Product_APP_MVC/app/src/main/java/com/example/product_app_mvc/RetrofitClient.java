package com.example.product_app_mvc;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.ExecutorService;

public class RetrofitClient {
    private static final String BASE_URL = "https://dummyjson.com/";
    private static RetrofitClient instance;
    private ProductService productsApi;
    private ExecutorService executorService;
    private Call<ProductsResponse> call;
    // Constructor
    private RetrofitClient(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        productsApi = retrofit.create(ProductService.class);
        //executorService = Executors.newSingleThreadExecutor(); // For background tasks

    }

    // Get RetrofitClient instance
    public static synchronized RetrofitClient getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    // Fetch products from the API
    public void fetchAndStoreProducts(NetworkCallback myNetworkCallback) {
        call = productsApi.getProducts();
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    myNetworkCallback.onSuccessResult(response.body().getProducts());

                } else {
                    Log.e("RetrofitClient", "API call failed");
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                myNetworkCallback.onFailureResult(t.getMessage());
                Log.e("RetrofitClient", "API call error: " + t.getMessage());
            }
        });
    }

}
