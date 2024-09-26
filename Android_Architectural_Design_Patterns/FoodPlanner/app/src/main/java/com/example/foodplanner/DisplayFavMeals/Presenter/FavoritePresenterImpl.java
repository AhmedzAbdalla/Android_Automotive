package com.example.foodplanner.DisplayFavMeals.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DisplayFavMeals.View.FavView;
import com.example.foodplanner.model.POJO_class;
import com.example.foodplanner.model.MealsRepository;

import java.util.List;

public class FavoritePresenterImpl implements FavoritePresenter{

    private FavView _view;
    private MealsRepository _repo;
    LiveData<List<POJO_class>> temp;

    public FavoritePresenterImpl(FavView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public LiveData<List<POJO_class>> getAllProducts() {
        temp = _repo.getStoredProducts();
        return temp;
    }

    @Override
    public void removeFromFav(POJO_class temp) {
        _repo.deleteProduct(temp);
    }
}
