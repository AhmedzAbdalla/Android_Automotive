package com.example.foodplanner.DisplayMealDetails.Presenter;

import com.example.foodplanner.DisplayMealDetails.Viewer.MealDetailsView;
import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.SearchForMeals.Viewer.SearchFragmentView;
import com.example.foodplanner.model.MealsRepository;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

public class DisplayMealDetailsPresenterImpl implements DisplayMealDetailsPresenter, NetworkCallback {
    private MealDetailsView _view;
    private MealsRepository _repo;

    public DisplayMealDetailsPresenterImpl(MealDetailsView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMealDetails(int l_mealID) {
        this._repo.getMealDetails(this,l_mealID);
    }

    @Override
    public void onSuccessResult(List<POJO_class> myproducts) {
        _view.showMealDisplay(myproducts);
    }

    @Override
    public void onFailureResult(String ErrorMsg) {

    }
}
