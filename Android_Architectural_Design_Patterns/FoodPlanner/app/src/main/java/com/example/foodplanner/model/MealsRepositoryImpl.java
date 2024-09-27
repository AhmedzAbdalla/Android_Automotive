package com.example.foodplanner.model;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DB.MealsLocalDataSourceImpl;
import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;

import java.util.List;

public class MealsRepositoryImpl implements  MealsRepository {

    MealsRemoteDataSourceImpl myMealssRemoteDataSourceImpl;
   MealsLocalDataSourceImpl myMealsLocalDataSourceImpl;

   private static MealsRepositoryImpl myrepo = null;

   public static MealsRepositoryImpl getInstance(MealsRemoteDataSourceImpl Remote_Src, MealsLocalDataSourceImpl Local_Src)
   {
       if (myrepo == null) {
           myrepo = new MealsRepositoryImpl(Remote_Src , Local_Src);
       }
       return myrepo;
   }

   private MealsRepositoryImpl(MealsRemoteDataSourceImpl Remote_Src, MealsLocalDataSourceImpl Local_Src)
   {
       this.myMealsLocalDataSourceImpl = Local_Src;
       this.myMealssRemoteDataSourceImpl = Remote_Src;
   }

    @Override
    public LiveData<List<POJO_class>> getStoredProducts() {
        return myMealsLocalDataSourceImpl.getStoredData();
    }

    @Override
    public void getMealDetails(NetworkCallback L_NetworkCallback, int mealID) {
        myMealssRemoteDataSourceImpl.fetchMealDetails(L_NetworkCallback, mealID);
    }

    @Override
    public void searchMealbyIngredient(NetworkCallback L_NetworkCallback, String prompt) {
        myMealssRemoteDataSourceImpl.searchForMealbyIngredient(L_NetworkCallback, prompt);
    }

    @Override
    public void searchMealbyCountry(NetworkCallback L_NetworkCallback, String prompt) {
        myMealssRemoteDataSourceImpl.searchForMealbyCountry(L_NetworkCallback , prompt);
    }

    @Override
    public void searchMealbyCategory(NetworkCallback L_NetworkCallback, String prompt) {
        myMealssRemoteDataSourceImpl.searchForMealbyCategory(L_NetworkCallback , prompt);
    }

    @Override
    public void getRandomMeal(NetworkCallback L_NetworkCallback) {
        myMealssRemoteDataSourceImpl.fetchRandomMeal(L_NetworkCallback);
    }

    @Override
    public void insertProduct(POJO_class L_POPojoClass) {
        myMealsLocalDataSourceImpl.insert(L_POPojoClass);
    }

    @Override
    public void deleteProduct(POJO_class L_POPojoClass) {
        myMealsLocalDataSourceImpl.delete(L_POPojoClass);
    }

}
