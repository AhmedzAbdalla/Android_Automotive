package com.example.foodplanner.DB;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.POJO_class;

import java.util.List;

public class MealsLocalDataSourceImpl implements MealsLocalDataSource {

    private Context context;
    private Fav_Meal_DB_DAO l_DAO;

    private LiveData<List<POJO_class>> storedProducts;

    private static MealsLocalDataSourceImpl repo = null;

    private MealsLocalDataSourceImpl(Context _context)
    {

        this.context = _context;
        DB_Creator DB = DB_Creator.getIlnstance(context.getApplicationContext());
        l_DAO = DB.getFav_Product_DB_DAO();
        storedProducts = l_DAO.getFavProduct();
        Log.i("TAG", "datavvvvvvvv");
    }

    public static MealsLocalDataSourceImpl getInstance(Context _context)
    {
        if(repo == null) {
            repo = new MealsLocalDataSourceImpl(_context);
        }
        return  repo;
    }

    //get Meals from database
    public LiveData<List<POJO_class>> getStoredData() {

        if(storedProducts == null)
        {
            Log.i("TAG", "NILLL");
        }
        else
        {
            Log.i("TAG", "bbb");
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

