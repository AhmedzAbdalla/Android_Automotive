package com.example.product_app_mvc;

import android.content.Context;
import android.graphics.Movie;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

public class Repository {

    private Context context;
    private Fav_Product_DB_DAO l_DAO;

    private LiveData<List<POJO_class>> storedProducts;

    private static Repository repo = null;

    private Repository(Context _context)
    {

        this.context = _context;
        DB_Creator DB = DB_Creator.getIlnstance(context.getApplicationContext());
        l_DAO = DB.getFav_Product_DB_DAO();
        storedProducts = l_DAO.getFavProduct();
        Log.i("TAG", "datavvvvvvvv");
    }

    public static Repository getInstance(Context _context)
    {
        if(repo == null) {
            repo = new Repository(_context);
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

    public LiveData<Integer> getProductCount() {
        return l_DAO.getProductCount();
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

