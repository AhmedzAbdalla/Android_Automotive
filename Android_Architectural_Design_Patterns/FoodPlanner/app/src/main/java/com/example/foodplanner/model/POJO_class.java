package com.example.foodplanner.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Entity class for the products table in Room
@Entity(tableName = "FavProducts")
public class POJO_class {
    @PrimaryKey
    @NonNull
    private String idMeal;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;

    // Getters and Setters
    public String getIdMeal() { return idMeal; }
    public void setIdMeal(String idMeal) { this.idMeal = idMeal; }

    public String getStrMeal() { return strMeal; }
    public void setStrMeal(String strMeal) { this.strMeal = strMeal; }

    public String getStrCategory() { return strCategory; }
    public void setStrCategory(String strCategory) { this.strCategory = strCategory; }

    public String getStrArea() { return strArea; }
    public void setStrArea(String strArea) { this.strArea = strArea; }

    public String getStrInstructions() { return strInstructions; }
    public void setStrInstructions(String strInstructions) { this.strInstructions = strInstructions; }

    public String getStrMealThumb() { return strMealThumb; }
    public void setStrMealThumb(String strMealThumb) { this.strMealThumb = strMealThumb; }

   //@Override
   //public String toString() {
   //    return "POJO_class{" +
   //            "title='" + title + '\'' +
   //            ", price=" + price +
   //            ", ID=" + id +
   //            ", description='" + description + '\'' +
   //            ", img_src='" + thumbnail + '\'' +

   //            '}' + "\n";
   //}


}

