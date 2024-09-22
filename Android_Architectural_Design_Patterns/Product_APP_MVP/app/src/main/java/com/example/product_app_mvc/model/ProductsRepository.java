package com.example.product_app_mvc.model;

import androidx.lifecycle.LiveData;

import com.example.product_app_mvc.Network.NetworkCallback;

import java.util.List;

public interface ProductsRepository {



    public LiveData<List<POJO_class>> getStoredProducts();

    public void getAllProducts(NetworkCallback L_NetworkCallback);

    public void insertProduct(POJO_class L_POPojoClass);

    public void deleteProduct(POJO_class L_POPojoClass);
}
