package com.example.foodplanner.DB;

import androidx.room.TypeConverter;

import com.example.foodplanner.model.POJO_class;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;

public class Converter {

    @TypeConverter
    public static Long fromDate(Date date) {
        return date != null ? date.getTime() : null;
    }

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp != null ? new Date(timestamp) : null;
    }

    @TypeConverter
    public String fromMeal(POJO_class meal) {
        if (meal == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(meal);
    }

    @TypeConverter
    public POJO_class toMeal(String mealString) {
        if (mealString == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<POJO_class>() {}.getType();
        return gson.fromJson(mealString, type);
    }
}
