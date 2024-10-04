package com.example.foodplanner.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "planMeal_table")
public class PlannedMeal {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String idMeal;

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    @ColumnInfo(name = "date")
    private Date date;
    private String mealType;
    private POJO_class meal;

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }


    public String getMealType() {
        return mealType;
    }

    public void setMeal(POJO_class meal) {
        this.meal = meal;
    }

    public POJO_class getMeal() {
        return meal;
    }

    public int getId() {
        return id;
    }


    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDate(Date date) {
        // Create a Calendar instance and set only the year, month, and day.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Set the normalized date (with time cleared)
        this.date = calendar.getTime();
    }

    public PlannedMeal() {
    }

    public PlannedMeal(POJO_class meal, Date day, String idMeal, String mealType) {
        this.meal = meal;
        this.date = day;
        this.mealType = mealType;
        this.idMeal = idMeal;

    }
}
