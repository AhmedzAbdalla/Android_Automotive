package com.example.foodplanner.Planner.Viewer;

import com.example.foodplanner.model.PlannedMeal;

public interface onPlanClickListener {

    void onDelClicked(PlannedMeal meal);
    void onMealItemClicked(PlannedMeal l_meal);
}
