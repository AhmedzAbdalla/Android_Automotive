package com.example.foodplanner.DB;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.POJO_class;

import java.util.List;

public interface MealsLocalDataSource {



    public LiveData<List<POJO_class>> getStoredData();

    public LiveData<Integer> getProductCount();

    public void delete(POJO_class Product);

    public void insert(POJO_class Product);
}
