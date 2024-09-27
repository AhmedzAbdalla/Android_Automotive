package com.example.foodplanner.Network;

public interface MealsRemoteDataSource {

    public void fetchMealDetails(NetworkCallback myNetworkCallback, int l_mealID);

    public void searchForMealbyIngredient(NetworkCallback myNetworkCallback, String mealName);

    public void searchForMealbyCountry(NetworkCallback myNetworkCallback, String CountryName);

    public void searchForMealbyCategory(NetworkCallback myNetworkCallback, String CategoryName);

    public void fetchRandomMeal(NetworkCallback myNetworkCallback);

}
