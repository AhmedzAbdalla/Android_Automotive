package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Network.NetworkCallback;

import java.util.Date;
import java.util.List;

public interface MealsRepository {



    public LiveData<List<POJO_class>> getStoredProducts();

    public void getMealDetails(NetworkCallback L_NetworkCallback, int mealID);

    public void searchMealbyIngredient(NetworkCallback L_NetworkCallback, String prompt);

    public void searchMealbyCountry(NetworkCallback L_NetworkCallback, String prompt, int l_flag);

    public void searchMealbyCategory(NetworkCallback L_NetworkCallback, String prompt);

    public void getRandomMeal(NetworkCallback L_NetworkCallback);

    public void getMealsCategories(NetworkCallback L_NetworkCallback);

    public void getMealsCountries(NetworkCallback L_NetworkCallback);


    public void insertProduct(POJO_class L_POPojoClass);

    public void deleteProduct(POJO_class L_POPojoClass);

    //=================================

    LiveData<List<PlannedMeal>> getMealForDay(Date day);

    public LiveData<List<PlannedMeal>> getPlannedMeals();

    public void insertPlannedMeal(PlannedMeal L_POPojoClass , Date date);

    public void deletePlannedMeal(PlannedMeal L_POPojoClass);
}
