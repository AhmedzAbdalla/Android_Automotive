package com.example.product_app_mvc.AllProducts.Presenter;

import com.example.product_app_mvc.model.POJO_class;

public interface AllProductsPresenter {
    public void getProducts();
    public void addToFav(POJO_class myProduct);

}
