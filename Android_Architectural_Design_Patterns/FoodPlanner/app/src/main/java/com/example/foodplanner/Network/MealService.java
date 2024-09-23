package com.example.foodplanner.Network;



import com.example.foodplanner.model.MealsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

// Interface for the Products API
public interface MealService {
    // Get all products or apply a limit query if needed
    @GET("products")
    Call<MealsResponse> getProducts();

    @POST("search.php")
    Call<MealsResponse> searchMeal(@Query("s") String mealName);
}
