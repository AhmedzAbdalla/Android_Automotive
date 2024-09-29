package com.example.foodplanner.listingbycategory.View;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.example.foodplanner.DB.MealsLocalDataSourceImpl;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.listingbycategory.Presenter.listingbycategoryPresenterImpl;
import com.example.foodplanner.model.MealsRepositoryImpl;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

public class Activity_MealsbyCatgeory extends AppCompatActivity implements CategoryMealsView {

    RecyclerView myrecyclerView;
    Adapter_item_meal_for_listing myAdapter_item_meal_for_listing;
    listingbycategoryPresenterImpl mylistingbycategoryPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mealsby_catgeory);

        String mealName = getIntent().getStringExtra("CategoryName");

        // Initialize RecyclerView
        myrecyclerView = findViewById(R.id.recyclerViewCategoryMeals);
        myrecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myrecyclerView.setLayoutManager(layoutManager);

        myAdapter_item_meal_for_listing = new Adapter_item_meal_for_listing(this);
        myrecyclerView.setAdapter(myAdapter_item_meal_for_listing);


        mylistingbycategoryPresenterImpl = new listingbycategoryPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(this), MealsLocalDataSourceImpl.getInstance(this)));

        mylistingbycategoryPresenterImpl.getCategoryMeals(mealName);



        Toast.makeText(this, "Clicked: " + mealName, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void displayCatgeoryMeals(List<POJO_class> l_list) {

        myAdapter_item_meal_for_listing.setList(l_list);
        myAdapter_item_meal_for_listing.notifyDataSetChanged();


    }
}