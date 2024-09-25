package com.example.foodplanner.Network;



import com.example.foodplanner.model.MealsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

// Interface for the Products API
public interface MealService {

    @POST("lookup.php")
    Call<MealsResponse> getMealDetails(@Query("i") int mealID);//search by Ingredient


    @POST("filter.php")
    Call<MealsResponse> searchMealbyIngredient(@Query("i") String mealName);//search by Ingredient

    @POST("filter.php")
    Call<MealsResponse> searchMealbyCountry(@Query("a") String CountryName);//search by Country

    @POST("filter.php")
    Call<MealsResponse> searchMealbyCategory(@Query("c") String CountryName);//search by Category
}
