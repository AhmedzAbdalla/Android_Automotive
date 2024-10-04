package com.example.foodplanner.Planner.Viewer;

import com.example.foodplanner.model.PlannedMeal;

import java.util.List;

public interface PlannerView {

    void showMeals(List<PlannedMeal> meals);
    void showErr(String error);
    void showDatemeal(List<PlannedMeal> meals);

}
