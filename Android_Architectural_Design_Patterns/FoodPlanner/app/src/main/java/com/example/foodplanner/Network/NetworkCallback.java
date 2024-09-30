package com.example.foodplanner.Network;

import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.Category_Pojo;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

public interface NetworkCallback {

    public void onSuccessResult(List<POJO_class> myproducts, int flag);
    public void onFailureResult(String ErrorMsg);

    public void onSuccessResultCat(List<Category_Pojo> myproducts);
    public void onFailureResultCat(String ErrorMsg);
}
