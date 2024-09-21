package com.example.product_app_mvc;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public interface NetworkCallback {

    public void onSuccessResult(List<POJO_class> myproducts);
    public void onFailureResult(String ErrorMsg);
}
