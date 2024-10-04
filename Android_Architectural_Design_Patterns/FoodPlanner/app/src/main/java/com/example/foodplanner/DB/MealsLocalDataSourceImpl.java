package com.example.foodplanner.DB;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.POJO_class;
import com.example.foodplanner.model.PlannedMeal;

import java.util.Date;
import java.util.List;

public class MealsLocalDataSourceImpl implements MealsLocalDataSource {

    private Context context;
    private Fav_Meal_DB_DAO l_DAO;
    private Planned_Meal_DB_DAO l_DAO_plan;

    private LiveData<List<POJO_class>> storedMealss;
    private LiveData<List<PlannedMeal>> PlannedMealss;

    private static MealsLocalDataSourceImpl repo = null;

    private MealsLocalDataSourceImpl(Context _context)
    {

        this.context = _context;
        DB_Creator DB = DB_Creator.getIlnstance(context.getApplicationContext());
        l_DAO = DB.getFav_Product_DB_DAO();
        l_DAO_plan = DB.getPlanned_Meal_DB_DAO();
        storedMealss = l_DAO.getFavProduct();
        PlannedMealss = l_DAO_plan.getAllPlannedMeals();
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

        if(storedMealss == null)
        {
            Log.i("TAG", "NILLL");
        }
        else
        {
            Log.i("TAG", "bbb");
        }
        return storedMealss;

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

    @Override
    public LiveData<List<PlannedMeal>> getAllPlannedMeals() {
        if(PlannedMealss == null)
        {
            Log.i("TAG", "NILLL");
        }
        else
        {
            Log.i("TAG", "bbb");
        }
        return PlannedMealss;
    }

    @Override
    public LiveData<List<PlannedMeal>> getMealForDay(Date day) {
        return l_DAO_plan.getMealForDay(day);
    }

    @Override
    public void insertMealToCalendar(PlannedMeal meal, Date date) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                meal.setDate(date);
                l_DAO_plan.insertPLannedMeal(meal);
            }
        }).start();


    }

    @Override
    public LiveData<PlannedMeal> getPlanMealById(String id) {
        return l_DAO_plan.getPlanMealById(id);
    }

    @Override
    public void deletePlannedMeal(PlannedMeal Meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                l_DAO_plan.deletePlannedMeal(Meal);
            }
        }).start();

    }
}

