package com.example.foodplanner.DisplayFavMeals.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.POJO_class;

import java.util.List;

public interface FavoritePresenter {

    public LiveData<List<POJO_class>> getAllProducts();
    public void removeFromFav(POJO_class temp);
}
