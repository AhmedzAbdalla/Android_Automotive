package com.example.foodplanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.DB.MealsLocalDataSourceImpl;
import com.example.foodplanner.DisplayFavMeals.Presenter.FavoritePresenterImpl;
import com.example.foodplanner.DisplayMealDetails.Viewer.MealDetailsActivity;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.RandomMeal.Presenter.RandomMealPresenterImpl;
import com.example.foodplanner.RandomMeal.Viewer.OnRandomItemClickListener;
import com.example.foodplanner.RandomMeal.Viewer.RandomMealView;
import com.example.foodplanner.listingbycategory.Presenter.listingbycategoryPresenterImpl;
import com.example.foodplanner.listingbycategory.View.Activity_MealsbyCatgeory;
import com.example.foodplanner.listingbycategory.View.AdapterOfCategorylisting;
import com.example.foodplanner.listingbycategory.View.OnCategoryItemClickListener;
import com.example.foodplanner.listingbycategory.View.listingbycategoryView;
import com.example.foodplanner.listingbycountry.Presenter.listingbycountryPresenterImpl;
import com.example.foodplanner.listingbycountry.Viewer.AdapterOfCountrylisting;
import com.example.foodplanner.listingbycountry.Viewer.OnCountryItemClickListener;
import com.example.foodplanner.listingbycountry.Viewer.listingbycountryView;
import com.example.foodplanner.model.Category_Pojo;
import com.example.foodplanner.model.MealsRepositoryImpl;
import com.example.foodplanner.model.POJO_class;

import java.util.ArrayList;
import java.util.List;

public class Fragment_home extends Fragment implements RandomMealView, OnRandomItemClickListener, listingbycategoryView, listingbycountryView,OnCategoryItemClickListener, OnCountryItemClickListener {

    RandomMealPresenterImpl myRandomMealPresenterImpl;
    List<POJO_class> MyRandomeMeal = new ArrayList<>();

    //========================== for listing by category
    RecyclerView myrecyclerViewcategorylist;
    LiveData<List<POJO_class>> MealsCategories;

    listingbycategoryPresenterImpl mylistingbycategoryPresenterImpl;
    AdapterOfCategorylisting myAdapterOfCategorylisting;
    //==========================

    //==========================
    RecyclerView myrecyclerViewCountrylist;
    AdapterOfCountrylisting myAdapterOfCountrylisting;
    listingbycountryPresenterImpl mylistingbycountryPresenterImpl;

    //==========================

    TextView textBelowImage;
    ImageView imageView;

    //int [] imageResources;

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textBelowImage = view.findViewById(R.id.textBelowImage);
        imageView = view.findViewById(R.id.imageView);

        // Find the inner ConstraintLayout container
        View innerContainer = view.findViewById(R.id.container);

        //====================== for listing by category



        // Initialize RecyclerView
        myrecyclerViewcategorylist = view.findViewById(R.id.recyclerViewCategories);
        myrecyclerViewcategorylist.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        myrecyclerViewcategorylist.setLayoutManager(layoutManager);


        myAdapterOfCategorylisting = new AdapterOfCategorylisting(this.getContext() , this);
        myrecyclerViewcategorylist.setAdapter(myAdapterOfCategorylisting);

        mylistingbycategoryPresenterImpl = new listingbycategoryPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(getContext()), MealsLocalDataSourceImpl.getInstance(getContext())));


       //mylistingbycategoryPresenterImpl.getMealsCatgeories();
        //==========================

        myrecyclerViewCountrylist = view.findViewById(R.id.recyclerViewCountry);
        myrecyclerViewCountrylist.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        myrecyclerViewCountrylist.setLayoutManager(layoutManager2);

        // Array of your local image resources
        int[] imageResources = {
                R.drawable.american,
                R.drawable.british,
                R.drawable.canadian,
                R.drawable.chinese,
                R.drawable.croatian,
                R.drawable.dutch,
                R.drawable.egyptian,
                R.drawable.filipino,
                R.drawable.french,
                R.drawable.greek,
                R.drawable.indian,
                R.drawable.irish,
                R.drawable.italian,
                R.drawable.jamaican,
                R.drawable.japanese,
                R.drawable.kenyan,
                R.drawable.malaysian,
                R.drawable.mexican,
                R.drawable.moroccan,
                R.drawable.polish,
                R.drawable.portuguese,
                R.drawable.russian,
                R.drawable.spanish,
                R.drawable.thai,
                R.drawable.tunisian,
                R.drawable.turkish,
                R.drawable.ukrainian,
                R.drawable.baseline_image_not_supported_24,
                R.drawable.vietnamese,};

        myAdapterOfCountrylisting = new AdapterOfCountrylisting(this.getContext() , this, imageResources);
        myrecyclerViewCountrylist.setAdapter(myAdapterOfCountrylisting);

        mylistingbycountryPresenterImpl = new listingbycountryPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(getContext()), MealsLocalDataSourceImpl.getInstance(getContext())));

        //if (NetworkUtils.isInternetAvailable(getContext())) {
        //    mylistingbycountryPresenterImpl.getMealsCountries();
        //} else {
        //    Toast.makeText(getContext(), "Internet Failure", Toast.LENGTH_SHORT).show();
//
        //}



        //==========================


        //==========================




        myRandomMealPresenterImpl = new RandomMealPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(getContext()), MealsLocalDataSourceImpl.getInstance(getContext())));

        Log.i("TAG", "vfdv: ");
        // Check for internet connection before making any network requests
        if (NetworkUtils.isInternetAvailable(getContext())) {
            // If internet is available, make network calls
            mylistingbycategoryPresenterImpl.getMealsCatgeories();
            mylistingbycountryPresenterImpl.getMealsCountries();
            myRandomMealPresenterImpl.getRandomMeal();
            innerContainer.setVisibility(View.VISIBLE);
        } else {
            // No internet connection, show a message to the user
            innerContainer.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
        }
       // myRandomMealPresenterImpl.getRandomMeal();

        // Set an onClickListener for the container
        innerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event here

                if (NetworkUtils.isInternetAvailable(getContext())) {
                    if((!MyRandomeMeal.isEmpty()))
                    {
                        onItemClick(MyRandomeMeal.get(0));
                    }
                    else {
                        //innerContainer.setVisibility(View.VISIBLE);
                    }

                    Toast.makeText(getContext(), "Container clicked", Toast.LENGTH_SHORT).show();

                } else {
                    // No internet connection, show a message to the user
                    Toast.makeText(getContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }
                }
        });


        return view;
    }

    @Override
    public void displayRandomMeal(List<POJO_class> l_list) {

       Glide.with(this.getActivity().getApplicationContext())
               .load(l_list.get(0).getStrMealThumb())
               .apply(new RequestOptions()
                       .override(200, 200)
                       .placeholder(R.drawable.ic_launcher_foreground)
                       .error(R.drawable.ic_launcher_foreground))
               .into(imageView);

        MyRandomeMeal = l_list;
        textBelowImage.setText(l_list.get(0).getStrMeal());
    }

    @Override
    public void onItemClick(POJO_class item) {
        //====================
        // Create an Intent to start MealDetailActivity
        Intent intent = new Intent(getContext(), MealDetailsActivity.class);

        // Pass data (the meal name) to the new activity
        intent.putExtra("meal_ID", item.getIdMeal());
        intent.putExtra("code", "ntest");

        // Start the new activity
        startActivity(intent);
        //====================

        Log.i("TAG", "onItemClick");
        Toast.makeText(getContext(), "Clicked: " + item.getIdMeal(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void displayMealsCatgeories(List<Category_Pojo> l_list) {

        Log.i("TAG", l_list.get(0).getStrCategory());
        myAdapterOfCategorylisting.setList(l_list);
        myAdapterOfCategorylisting.notifyDataSetChanged();
    }

    @Override
    public void displayMealsCountries(List<POJO_class> l_list) {
        //Log.i("TAG", "&&&&&&&&&&&&&&&&&&");
        //Log.i("TAG", l_list.get(0).getStrArea());
        myAdapterOfCountrylisting.setList(l_list);
        myAdapterOfCountrylisting.notifyDataSetChanged();
    }

    @Override
    public void onCategoryItemClick(Category_Pojo item) {
        // Create an Intent to start MealDetailActivity
        Intent intent = new Intent(getContext(), Activity_MealsbyCatgeory.class);

        // Pass data (the meal name) to the new activity
        intent.putExtra("CategoryName", item.getStrCategory());
        intent.putExtra("code", "categoryReq");

        // Start the new activity
        startActivity(intent);
        //====================

        Log.i("TAG", "onItemClick");
        Toast.makeText(getContext(), "Clicked: " + item.getStrCategory(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCountryItemClick(POJO_class item) {
        // Create an Intent to start MealDetailActivity
        Intent intent = new Intent(getContext(), Activity_MealsbyCatgeory.class);

        // Pass data (the meal name) to the new activity
        intent.putExtra("CountryName", item.getStrArea());
        intent.putExtra("code", "countryReq");

        // Start the new activity
        startActivity(intent);
        //====================

        Log.i("TAG", "onItemClick");
        Toast.makeText(getContext(), "Clicked: " + item.getStrArea(), Toast.LENGTH_SHORT).show();

    }
}