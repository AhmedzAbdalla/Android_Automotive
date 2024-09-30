package com.example.foodplanner.listingbycountry.Presenter;

import android.util.Log;

import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.listingbycountry.Viewer.CountryMealsView;
import com.example.foodplanner.listingbycountry.Viewer.listingbycountryView;
import com.example.foodplanner.model.Category_Pojo;
import com.example.foodplanner.model.MealsRepository;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

public class listingbycountryPresenterImpl implements listingbycountryPresenter, NetworkCallback {

    listingbycountryView _view;
    CountryMealsView _view2;
    private MealsRepository _repo;


    public listingbycountryPresenterImpl(listingbycountryView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    public listingbycountryPresenterImpl(CountryMealsView _view, MealsRepository _repo) {
        this._view2 = _view;
        this._repo = _repo;
    }

    //CountryMealsView

    @Override
    public void getMealsCountries() {
        _repo.getMealsCountries(this);
    }

    @Override
    public void getCountryMeals(String CountryName) {
        _repo.searchMealbyCountry(this, CountryName, 0);
    }


    @Override
    public void onSuccessResult(List<POJO_class> myproducts, int flag) {

        Log.i("TAG", "@@@@@@@@@@");
        //Log.i("TAG", myproducts.get(0).getStrArea());
        if(flag == 1)
        {
            _view.displayMealsCountries(myproducts);
        }
        else
        {
            _view2.displayCountryMeals(myproducts);
        }

        //_view2.displayCountryMeals(myproducts);
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
