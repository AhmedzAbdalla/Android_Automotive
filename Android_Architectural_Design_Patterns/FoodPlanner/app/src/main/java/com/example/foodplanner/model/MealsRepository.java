package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Network.NetworkCallback;

import java.util.List;

public interface MealsRepository {



    public LiveData<List<POJO_class>> getStoredProducts();

    public void getMealDetails(NetworkCallback L_NetworkCallback, int mealID);

    public void searchMealbyIngredient(NetworkCallback L_NetworkCallback, String prompt);

    public void searchMealbyCountry(NetworkCallback L_NetworkCallback, String prompt);

    public void searchMealbyCategory(NetworkCallback L_NetworkCallback, String prompt);

    public void getRandomMeal(NetworkCallback L_NetworkCallback);

    public void getMealsCategories(NetworkCallback L_NetworkCallback);

    public void insertProduct(POJO_class L_POPojoClass);

    public void deleteProduct(POJO_class L_POPojoClass);
}
