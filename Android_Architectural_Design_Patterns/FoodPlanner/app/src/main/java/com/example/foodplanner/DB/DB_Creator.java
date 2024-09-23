package com.example.foodplanner.DB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.POJO_class;

@Database(entities = {POJO_class.class}, version = 1, exportSchema = false)
public abstract class DB_Creator extends RoomDatabase {
    private static DB_Creator instance = null;

    /*this abstract method that returns a ref to Fav_Product_DB_DAO
    * b/c Room will create an object of class that implements Fav_Product_DB_DAO interface
    * and returns it*/
    public abstract Fav_Meal_DB_DAO getFav_Product_DB_DAO();

    public static synchronized DB_Creator getIlnstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DB_Creator.class, "DB_Fav_Products")
                    .build();
        }
        return instance;
    }

}