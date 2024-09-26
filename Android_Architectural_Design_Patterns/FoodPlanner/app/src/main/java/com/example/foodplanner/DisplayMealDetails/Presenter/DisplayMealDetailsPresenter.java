package com.example.foodplanner.DisplayMealDetails.Presenter;

import com.example.foodplanner.model.POJO_class;

public interface DisplayMealDetailsPresenter {
    public void getMealDetails(int l_mealID);
    public void addToFav(POJO_class myProduct);
}
