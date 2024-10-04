package com.example.foodplanner.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.PlannedMeal;

import java.util.Date;
import java.util.List;

@Dao
public interface Planned_Meal_DB_DAO {
    @Query("SELECT * FROM planMeal_table WHERE idMeal = :id LIMIT 1")
    LiveData<PlannedMeal> getPlanMealById(String id);

    @Query("SELECT * FROM planMeal_table WHERE date = :day")
    LiveData<List<PlannedMeal>> getMealForDay(Date day);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPLannedMeal(PlannedMeal meal);

    @Delete
    void deletePlannedMeal(PlannedMeal meal);

    @Query("SELECT * FROM planMeal_table")
    LiveData<List<PlannedMeal>> getAllPlannedMeals();
}
