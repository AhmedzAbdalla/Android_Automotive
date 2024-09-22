package com.example.product_app_mvc.FavProducts.Presenter;

import androidx.lifecycle.LiveData;

import com.example.product_app_mvc.model.POJO_class;

import java.util.List;

public interface FavoritePresenter {

    public LiveData<List<POJO_class>> getAllProducts();
    public void removeFromFav(POJO_class temp);
}
