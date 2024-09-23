package com.example.foodplanner.model;

import androidx.annotation.NonNull;

import java.util.List;

// Wrapper class for the response from the API
public class MealsResponse {
    private List<POJO_class> meals;

    public List<POJO_class> getMeals() {
        return meals;
    }

    public void setMeals(List<POJO_class> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public String toString() {
        return "ProductsResponse{" +
                "products=" + meals +
                '}';
    }

}
