package com.example.foodplanner.SearchForMeals.Presenter;

public interface SearchForMealsPresenter {
    public void getProductsbyIngredient(String l_meal);
    public void getProductsbyCountry(String l_country);
    public void getProductsbyCategory(String l_category);
    //public void addToFav(POJO_class myProduct);
}
