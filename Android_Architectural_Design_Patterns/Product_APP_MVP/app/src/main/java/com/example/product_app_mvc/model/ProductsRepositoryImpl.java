package com.example.product_app_mvc.model;

import androidx.lifecycle.LiveData;

import com.example.product_app_mvc.DB.ProductsLocalDataSourceImpl;
import com.example.product_app_mvc.Network.NetworkCallback;
import com.example.product_app_mvc.Network.ProductsRemoteDataSourceImpl;

import java.util.List;

public class ProductsRepositoryImpl implements ProductsRepository{

   ProductsRemoteDataSourceImpl myProductsRemoteDataSourceImpl;
   ProductsLocalDataSourceImpl myProductsLocalDataSourceImpl;

   private static ProductsRepositoryImpl myrepo = null;

   public static ProductsRepositoryImpl getInstance(ProductsRemoteDataSourceImpl Remote_Src, ProductsLocalDataSourceImpl Local_Src)
   {
       if (myrepo == null) {
           myrepo = new ProductsRepositoryImpl(Remote_Src , Local_Src);
       }
       return myrepo;
   }

   private ProductsRepositoryImpl(ProductsRemoteDataSourceImpl Remote_Src, ProductsLocalDataSourceImpl Local_Src)
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
