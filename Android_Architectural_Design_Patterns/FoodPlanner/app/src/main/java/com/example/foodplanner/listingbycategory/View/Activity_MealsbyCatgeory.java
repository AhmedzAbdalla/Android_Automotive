package com.example.foodplanner.listingbycategory.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.DB.MealsLocalDataSourceImpl;
import com.example.foodplanner.DisplayMealDetails.Viewer.MealDetailsActivity;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.listingbycategory.Presenter.listingbycategoryPresenterImpl;
import com.example.foodplanner.listingbycountry.Presenter.listingbycountryPresenterImpl;
import com.example.foodplanner.listingbycountry.Viewer.CountryMealsView;
import com.example.foodplanner.model.MealsRepositoryImpl;
import com.example.foodplanner.model.POJO_class;

import java.util.List;
import java.util.Objects;

public class Activity_MealsbyCatgeory extends AppCompatActivity implements CategoryMealsView , CategoryMealonItemClickListener, CountryMealsView {

    RecyclerView myrecyclerView;
    Adapter_item_meal_for_listing myAdapter_item_meal_for_listing;
    listingbycategoryPresenterImpl mylistingbycategoryPresenterImpl;
    listingbycountryPresenterImpl mylistingbycountryPresenterImpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mealsby_catgeory);

        String code = getIntent().getStringExtra("code");

        // Initialize RecyclerView
        myrecyclerView = findViewById(R.id.recyclerViewCategoryMeals);
        myrecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myrecyclerView.setLayoutManager(layoutManager);

        myAdapter_item_meal_for_listing = new Adapter_item_meal_for_listing(this , this);
        myrecyclerView.setAdapter(myAdapter_item_meal_for_listing);

        if(Objects.equals(code, "categoryReq"))
        {
            mylistingbycategoryPresenterImpl = new listingbycategoryPresenterImpl((CategoryMealsView)this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(this), MealsLocalDataSourceImpl.getInstance(this)));

            String mealName = getIntent().getStringExtra("CategoryName");

            mylistingbycategoryPresenterImpl.getCategoryMeals(mealName); //CountryName

        }
        else if(Objects.equals(code, "countryReq") )
        {
            String mealName = getIntent().getStringExtra("CountryName");

            mylistingbycountryPresenterImpl = new listingbycountryPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(this), MealsLocalDataSourceImpl.getInstance(this)));
            mylistingbycountryPresenterImpl.getCountryMeals(mealName);
            Toast.makeText(this, "i'm Clicked: " + mealName, Toast.LENGTH_SHORT).show();

        }



    }

    @Override
    public void displayCatgeoryMeals(List<POJO_class> l_list) {
        myAdapter_item_meal_for_listing.setList(l_list);
        myAdapter_item_meal_for_listing.notifyDataSetChanged();

    }

    @Override
    public void displayCountryMeals(List<POJO_class> l_list) {
        myAdapter_item_meal_for_listing.setList(l_list);
        myAdapter_item_meal_for_listing.notifyDataSetChanged();
    }

    @Override
    public void CategoryMealonItemClickListener(POJO_class item) {
        //====================
        // Create an Intent to start MealDetailActivity
        Intent intent = new Intent(this, MealDetailsActivity.class);

        // Pass data (the meal name) to the new activity
        intent.putExtra("meal_ID", item.getIdMeal());

        // Start the new activity
        startActivity(intent);
        //====================

        Log.i("TAG", "onItemClick");
        Toast.makeText(this, "Clicked: " + item.getIdMeal(), Toast.LENGTH_SHORT).show();

    }


}