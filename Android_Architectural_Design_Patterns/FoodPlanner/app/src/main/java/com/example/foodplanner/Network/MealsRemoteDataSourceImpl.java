package com.example.foodplanner.Network;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.model.MealsResponse;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImpl implements MealsRemoteDataSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static MealsRemoteDataSourceImpl instance;
    private MealService productsApi;
    private Call<MealsResponse> call;
    // Constructor
    private MealsRemoteDataSourceImpl(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        productsApi = retrofit.create(MealService.class);
        //executorService = Executors.newSingleThreadExecutor(); // For background tasks

    }

    // Get RetrofitClient instance
    public static synchronized MealsRemoteDataSourceImpl getInstance(Context context) {
        if (instance == null) {
            instance = new MealsRemoteDataSourceImpl(context);
        }
        return instance;
    }

    // Fetch products from the API
    public void fetchAndStoreProducts(NetworkCallback myNetworkCallback) {
        call = productsApi.getProducts();
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    myNetworkCallback.onSuccessResult(response.body().getMeals());
                    Log.i("RetrofitClient", "here");
                } else {
                    Log.e("RetrofitClient", "API call failed");
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                myNetworkCallback.onFailureResult(t.getMessage());
                Log.e("RetrofitClient", "API call error: " + t.getMessage());
            }
        });



    }


    public void searchForMealbyIngredient(NetworkCallback myNetworkCallback, String mealName) {
        call = productsApi.searchMealbyIngredient(mealName);

        call.enqueue(new retrofit2.Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, retrofit2.Response<MealsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<POJO_class> meals = response.body().getMeals();
                    // Handle the meal data here

                    myNetworkCallback.onSuccessResult(response.body().getMeals());

                    //for (POJO_class meal : meals) {
                    //    Log.i("TAG", "ccccccccccccc");
                    //    Log.i("Meal", "Meal Name: " + meal.getStrMeal());
                    //    Log.i("Meal", "Meal Name: " + meal.getStrCategory());
                    //    // Do something with the meal data
                    //}
                } else {
                    Log.e("API_ERROR", "Response unsuccessful or body is null");
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                myNetworkCallback.onFailureResult("No Response");
                Log.e("API_ERROR", "Request failed: " + t.getMessage());
            }
        });
    }

    @Override
    public void searchForMealbyCountry(NetworkCallback myNetworkCallback, String CountryName) {
        call = productsApi.searchMealbyCountry(CountryName);

        call.enqueue(new retrofit2.Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, retrofit2.Response<MealsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<POJO_class> meals = response.body().getMeals();
                    // Handle the meal data here

                    myNetworkCallback.onSuccessResult(response.body().getMeals());

                    //for (POJO_class meal : meals) {
                        Log.i("TAG", "ccccppc");
                    //    Log.i("Meal", "Meal Name: " + meal.getStrMeal());
                    //    Log.i("Meal", "Meal Name: " + meal.getStrCategory());
                    //    // Do something with the meal data
                    //}
                } else {
                    Log.e("API_ERROR", "Response unsuccessful or body is null");
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                myNetworkCallback.onFailureResult("No Response");
                Log.e("API_ERROR", "Request failed: " + t.getMessage());
            }
        });
    }

    @Override
    public void searchForMealbyCategory(NetworkCallback myNetworkCallback, String CategoryName) {
        call = productsApi.searchMealbyCategory(CategoryName);

        call.enqueue(new retrofit2.Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, retrofit2.Response<MealsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<POJO_class> meals = response.body().getMeals();
                    // Handle the meal data here

                    myNetworkCallback.onSuccessResult(response.body().getMeals());

                    //for (POJO_class meal : meals) {
                    Log.i("TAG", "vgvj");
                    //    Log.i("Meal", "Meal Name: " + meal.getStrMeal());
                    //    Log.i("Meal", "Meal Name: " + meal.getStrCategory());
                    //    // Do something with the meal data
                    //}
                } else {
                    Log.e("API_ERROR", "Response unsuccessful or body is null");
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                myNetworkCallback.onFailureResult("No Response");
                Log.e("API_ERROR", "Request failed: " + t.getMessage());
            }
        });
    }

}
