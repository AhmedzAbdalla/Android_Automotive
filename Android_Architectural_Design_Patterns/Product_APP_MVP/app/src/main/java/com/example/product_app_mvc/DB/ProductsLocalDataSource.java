package com.example.product_app_mvc.DB;

import androidx.lifecycle.LiveData;

import com.example.product_app_mvc.model.POJO_class;

import java.util.List;

public interface ProductsLocalDataSource {



    public LiveData<List<POJO_class>> getStoredData();

    public LiveData<Integer> getProductCount();

    public void delete(POJO_class Product);

    public void insert(POJO_class Product);
}
