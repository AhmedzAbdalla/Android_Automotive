package com.example.product_app_mvc.DB;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.product_app_mvc.model.POJO_class;

import java.util.List;

/** @noinspection AndroidUnresolvedRoomSqlReference*/
@Dao
public interface Fav_Product_DB_DAO {
    //obsevable
    @Query("SELECT * FROM FavProducts")
    LiveData<List<POJO_class>> getFavProduct();

    @Insert(onConflict = IGNORE)
    void addFavProduct(POJO_class MyProduct);

    @Delete
    void removeFavProduct(POJO_class MyProduct);
}

