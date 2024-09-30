package com.example.foodplanner.listingbycategory.Presenter;

import android.util.Log;

import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.RandomMeal.Viewer.RandomMealView;
import com.example.foodplanner.listingbycategory.View.CategoryMealsView;
import com.example.foodplanner.listingbycategory.View.listingbycategoryView;
import com.example.foodplanner.listingbycountry.Viewer.CountryMealsView;
import com.example.foodplanner.model.Category_Pojo;
import com.example.foodplanner.model.MealsRepository;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

public class listingbycategoryPresenterImpl implements listingbycategoryPresenter, NetworkCallback {
    private listingbycategoryView _view;
    private MealsRepository _repo;
    private CategoryMealsView _view2;

    public listingbycategoryPresenterImpl(listingbycategoryView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    public listingbycategoryPresenterImpl(CategoryMealsView _view, MealsRepository _repo) {
        this._view2 = _view;
        this._repo = _repo;
    }


    @Override
    public void getMealsCatgeories() {
        _repo.getMealsCategories(this);

    }

    @Override
    public void getCategoryMeals(String CategoryName) {
        _repo.searchMealbyCategory( this ,CategoryName);
    }

    @Override
    public void onSuccessResult(List<POJO_class> myproducts, int flag) {
        _view2.displayCatgeoryMeals(myproducts);
        //Log.i("TAG", String.valueOf(myproducts.size()));
    }

    @Override
    public void onFailureResult(String ErrorMsg) {

    }

    @Override
    public void onSuccessResultCat(List<Category_Pojo> myproducts) {

        _view.displayMealsCatgeories(myproducts);
    }

    @Override
    public void onFailureResultCat(String ErrorMsg) {

    }
}
