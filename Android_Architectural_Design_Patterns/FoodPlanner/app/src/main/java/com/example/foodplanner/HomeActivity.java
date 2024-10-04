package com.example.foodplanner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.foodplanner.DisplayFavMeals.View.Fragment_Favorite;
import com.example.foodplanner.Planner.Viewer.Fragment_Plan;
import com.example.foodplanner.SearchForMeals.Viewer.Fragment_Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    TextView CurrentFragmentName;
    BottomNavigationView bottomNavigationView;
    int yemp1,temp2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        //yemp1 =   = item.getItemId();
        CurrentFragmentName = findViewById(R.id.CurrentFragmentName);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //bottomNavigationView = findViewById(R.id.bottom_navigation);

        //MealsRemoteDataSourceImpl temp = MealsRemoteDataSourceImpl.getInstance(this);

        //temp.searchForMeal(this , "Arrabiata");
        // Load default fragment
        if (savedInstanceState == null) {
            CurrentFragmentName.setText("Home");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_home()).commit();
        }

        // BottomNavigationView bottomNavigationView = null;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                // Use if-else instead of switch-case
                if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = new Fragment_home();
                    CurrentFragmentName.setText("Home");
                    Log.i("TAG", "Home Selected");
                } else if (item.getItemId() == R.id.nav_favorite) {
                    //selectedFragment = new Fragment_Search();
                    selectedFragment = new Fragment_Favorite();
                    CurrentFragmentName.setText("Search");
                    Log.i("TAG", "Search Selected");
                }
                else if (item.getItemId() == R.id.nav_search) {
                    selectedFragment = new Fragment_Search();
                    CurrentFragmentName.setText("Favorites");
                    Log.i("TAG", "Favorites Selected");
                }
                else if (item.getItemId() == R.id.nav_plan) {
                    selectedFragment = new Fragment_Plan();
                    CurrentFragmentName.setText("My Plan");
                    Log.i("TAG", "Plans Selected");
                }


                //assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment, "csc").commit();
                return true;
            }
        });



    }

}
