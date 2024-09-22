package com.example.product_app_mvc.DB;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.product_app_mvc.model.POJO_class;

import java.util.List;

public class ProductsLocalDataSourceImpl implements ProductsLocalDataSource {

    private Context context;
    private Fav_Product_DB_DAO l_DAO;

    private LiveData<List<POJO_class>> storedProducts;

    private static ProductsLocalDataSourceImpl repo = null;

    private ProductsLocalDataSourceImpl(Context _context)
    {

        this.context = _context;
        DB_Creator DB = DB_Creator.getIlnstance(context.getApplicationContext());
        l_DAO = DB.getFav_Product_DB_DAO();
        storedProducts = l_DAO.getFavProduct();
        Log.i("TAG", "datavvvvvvvv");
    }

    public static ProductsLocalDataSourceImpl getInstance(Context _context)
    {
        if(repo == null) {
            repo = new ProductsLocalDataSourceImpl(_context);
        }
        return  repo;
    }

    //get movies from database
    public LiveData<List<POJO_class>> getStoredData() {

        if(storedProducts == null)
        {
            Log.i("TAG", "NILLL");
        }
        return storedProducts;

    }

    @Override
    public LiveData<Integer> getProductCount() {
        return null;
    }


    public void delete(POJO_class Product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                l_DAO.removeFavProduct(Product);
            }
        }).start();
    }

    public void insert(POJO_class Product) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                l_DAO.addFavProduct(Product);
            }
        }).start();
    }
}

