package com.example.foodplanner.Planner.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.PlannedMeal;

import java.util.Date;
import java.util.List;

public interface PlannerPresenter {

    void AddtoPlannedTable(PlannedMeal meal , Date date) ;
    void removeFromPlannedTable(PlannedMeal meal);
    LiveData<List<PlannedMeal>> getPlannedMealByDate(Date selectedDate);

}
