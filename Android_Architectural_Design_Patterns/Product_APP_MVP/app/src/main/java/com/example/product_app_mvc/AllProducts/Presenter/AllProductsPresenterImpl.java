package com.example.product_app_mvc.AllProducts.Presenter;

import android.util.Log;

import com.example.product_app_mvc.AllProducts.Viewer.Viewer.AllProductView;
import com.example.product_app_mvc.Network.NetworkCallback;
import com.example.product_app_mvc.model.POJO_class;
import com.example.product_app_mvc.model.ProductsRepository;

import java.util.List;

public class AllProductsPresenterImpl implements AllProductsPresenter, NetworkCallback {
    private AllProductView _view;
    private ProductsRepository _repo;


    public AllProductsPresenterImpl(AllProductView _view, ProductsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }


    @Override
    public void getProducts() {
        _repo.getAllProducts(this);
    }

    @Override
    public void addToFav(POJO_class myProduct) {
        _repo.insertProduct(myProduct);
    }

    @Override
    public void onSuccessResult(List<POJO_class> myproducts) {
        _view.showProducts(myproducts);
        Log.i("TAG", "cccccc");
    }

    @Override
    public void onFailureResult(String ErrorMsg) {
        //TBD
    }
}
