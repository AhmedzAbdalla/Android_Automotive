package com.example.foodplanner.SearchForMeals.Presenter;

import android.util.Log;

import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.SearchForMeals.Viewer.Fragment_Search;
import com.example.foodplanner.SearchForMeals.Viewer.SearchFragmentView;
import com.example.foodplanner.model.Category_Pojo;
import com.example.foodplanner.model.MealsRepository;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

public class SearchForMealsPresenterImpl implements SearchForMealsPresenter, NetworkCallback {

    private SearchFragmentView _view;
    private MealsRepository _repo;

    public SearchForMealsPresenterImpl(SearchFragmentView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void onSuccessResult(List<POJO_class> myproducts, int l_flag) {
        _view.showProducts(myproducts);
        Log.i("TAG", "cccccc");
    }

    @Override
    public void onFailureResult(String ErrorMsg) {
        Log.i("TAG", "qqqq");
    }

    @Override
    public void onSuccessResultCat(List<Category_Pojo> myproducts) {

    }

    @Override
    public void onFailureResultCat(String ErrorMsg) {

    }

    @Override
    public void getProductsbyIngredient(String l_meal) {
       _repo.searchMealbyIngredient(this, l_meal);
        Log.i("TAG", "mmmm");
    }

    @Override
    public void getProductsbyCountry(String l_country) {
        _repo.searchMealbyCountry(this,l_country, 1);
        Log.i("TAG", "mmmm");
    }

    @Override
    public void getProductsbyCategory(String l_category) {
        _repo.searchMealbyCategory(this,l_category);
    }

}
