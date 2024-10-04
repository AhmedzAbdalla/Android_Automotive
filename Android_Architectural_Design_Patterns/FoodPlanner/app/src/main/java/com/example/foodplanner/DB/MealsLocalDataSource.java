package com.example.foodplanner.DB;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.POJO_class;
import com.example.foodplanner.model.PlannedMeal;

import java.util.Date;
import java.util.List;

public interface MealsLocalDataSource {



    public LiveData<List<POJO_class>> getStoredData();

    public LiveData<Integer> getProductCount();

    public void delete(POJO_class Product);

    public void insert(POJO_class Product);

    //=============================
    LiveData<List<PlannedMeal>> getAllPlannedMeals();
    LiveData<List<PlannedMeal>> getMealForDay(Date day);
    void insertMealToCalendar(PlannedMeal meal , Date date);
    LiveData<PlannedMeal> getPlanMealById(String id);
    public void deletePlannedMeal(PlannedMeal Meal);
}
