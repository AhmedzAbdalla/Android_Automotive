package com.example.foodplanner.DisplayMealDetails.Presenter;

import android.util.Log;

import com.example.foodplanner.DisplayMealDetails.Viewer.MealDetailsView;
import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.SearchForMeals.Viewer.SearchFragmentView;
import com.example.foodplanner.model.Category_Pojo;
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
    public void onSuccessResult(List<POJO_class> myproducts, int flag) {
        _view.showMealDisplay(myproducts);
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

    @Override
    public void addToFav(POJO_class myProduct) {
        _repo.insertProduct(myProduct);
        Log.i("TAG", "addToFav");
    }

}
