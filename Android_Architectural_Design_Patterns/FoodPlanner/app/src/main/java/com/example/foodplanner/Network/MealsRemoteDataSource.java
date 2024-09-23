package com.example.foodplanner.Network;

public interface MealsRemoteDataSource {

    public void fetchAndStoreProducts(NetworkCallback myNetworkCallback);
    public void searchForMeal( NetworkCallback myNetworkCallback,String mealName);
}
