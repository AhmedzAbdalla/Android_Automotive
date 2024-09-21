package com.example.product_app_mvc;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/** @noinspection AndroidUnresolvedRoomSqlReference*/
@Dao
public interface Fav_Product_DB_DAO {
    //obsevable
    @Query("SELECT * FROM FavProducts")
    LiveData<List<POJO_class>> getFavProduct();
    //List<POJO_class> getFavProduct();

    @Query("SELECT COUNT(*) FROM FavProducts")
    LiveData<Integer> getProductCount();

    //LiveData creates a new thread then fetches the updated to send t the UI

    @Insert(onConflict = IGNORE)
    void addFavProduct(POJO_class MyProduct);

    @Delete
    void removeFavProduct(POJO_class MyProduct);
}

