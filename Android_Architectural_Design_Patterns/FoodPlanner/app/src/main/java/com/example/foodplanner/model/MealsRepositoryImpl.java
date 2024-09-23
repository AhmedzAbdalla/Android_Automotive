package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DB.MealsLocalDataSourceImpl;
import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;

import java.util.List;

public class MealsRepositoryImpl implements MealsRepository {

    MealsRemoteDataSourceImpl myProductsRemoteDataSourceImpl;
   MealsLocalDataSourceImpl myProductsLocalDataSourceImpl;

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
       this.myProductsLocalDataSourceImpl = Local_Src;
       this.myProductsRemoteDataSourceImpl = Remote_Src;
   }

    @Override
    public LiveData<List<POJO_class>> getStoredProducts() {
        return myProductsLocalDataSourceImpl.getStoredData();
    }

    @Override
    public void getAllProducts(NetworkCallback L_NetworkCallback) {
        myProductsRemoteDataSourceImpl.fetchAndStoreProducts(L_NetworkCallback);
    }

    @Override
    public void insertProduct(POJO_class L_POPojoClass) {
        myProductsLocalDataSourceImpl.insert(L_POPojoClass);
    }

    @Override
    public void deleteProduct(POJO_class L_POPojoClass) {
        myProductsLocalDataSourceImpl.delete(L_POPojoClass);
    }
}
