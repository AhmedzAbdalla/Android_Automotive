package com.example.foodplanner.Network;

import com.example.foodplanner.model.POJO_class;

import java.util.List;

public interface NetworkCallback {

    public void onSuccessResult(List<POJO_class> myproducts);
    public void onFailureResult(String ErrorMsg);
}
