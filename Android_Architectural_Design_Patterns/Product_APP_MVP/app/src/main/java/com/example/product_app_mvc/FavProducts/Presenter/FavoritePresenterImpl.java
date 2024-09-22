package com.example.product_app_mvc.FavProducts.Presenter;

import androidx.lifecycle.LiveData;

import com.example.product_app_mvc.FavProducts.View.FavView;
import com.example.product_app_mvc.model.POJO_class;
import com.example.product_app_mvc.model.ProductsRepository;

import java.util.List;

public class FavoritePresenterImpl implements FavoritePresenter{

    private FavView _view;
    private ProductsRepository _repo;
    LiveData<List<POJO_class>> temp;

    public FavoritePresenterImpl(FavView _view, ProductsRepository _repo) {
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
