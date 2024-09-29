package com.example.foodplanner.RandomMeal.Presenter;

import com.example.foodplanner.DisplayMealDetails.Viewer.MealDetailsView;
import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.RandomMeal.Viewer.RandomMealView;
import com.example.foodplanner.model.Category_Pojo;
import com.example.foodplanner.model.MealsRepository;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

public class RandomMealPresenterImpl implements RandomMealPresenter, NetworkCallback {

    private RandomMealView _view;
    private MealsRepository _repo;

    public RandomMealPresenterImpl(RandomMealView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getRandomMeal() {
        _repo.getRandomMeal(this);

    }

    @Override
    public void onSuccessResult(List<POJO_class> myproducts) {
        _view.displayRandomMeal(myproducts);
    }

    @Override
    public void onFailureResult(String ErrorMsg) {

    }

    @Override
    public void onSuccessResultCat(List<Category_Pojo> myproducts) {

    }

    @Override
    public void onFailureResultCat(String ErrorMsg) {

    }
}