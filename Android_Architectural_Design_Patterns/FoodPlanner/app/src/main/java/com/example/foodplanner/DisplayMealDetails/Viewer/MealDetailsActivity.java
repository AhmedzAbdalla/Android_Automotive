package com.example.foodplanner.DisplayMealDetails.Viewer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.DB.MealsLocalDataSourceImpl;
import com.example.foodplanner.DisplayMealDetails.Presenter.DisplayMealDetailsPresenterImpl;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.MealsRepositoryImpl;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsView , if_AddFavProduct{

    private TextView   txt_meal_name;
    private ImageView  img_meal;
    private TextView   txt_origin_country;
    private TextView   txt_steps;
    private TextView   txt_ingredients;
    private VideoView  video_view;
    private Button     btn_favorite;
    //SearchForMealsPresenterImpl mySearchForMealsImpl;

    DisplayMealDetailsPresenterImpl myDisplayMealDetailsPresenterImpl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_details);

        txt_meal_name = findViewById(R.id.txt_meal_name);
        img_meal = findViewById(R.id.img_meal);
        txt_origin_country = findViewById(R.id.txt_origin_country);
        txt_steps = findViewById(R.id.txt_steps);
        txt_ingredients = findViewById(R.id.txt_ingredients);
        video_view = findViewById(R.id.video_view);
        btn_favorite = findViewById(R.id.btn_delete);

        myDisplayMealDetailsPresenterImpl = new DisplayMealDetailsPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(this), MealsLocalDataSourceImpl.getInstance(this)));

        String mealName = getIntent().getStringExtra("meal_ID");
        //txt_meal_name.setText(mealName);
        myDisplayMealDetailsPresenterImpl.getMealDetails(Integer.valueOf(mealName));


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void showMealDisplay(List<POJO_class> l_list) {
        txt_meal_name.setText(l_list.get(0).getStrMeal());
        txt_origin_country.setText(l_list.get(0).getStrArea());
        txt_steps.setText(l_list.get(0).getStrInstructions());
        String temp = l_list.get(0).getStrIngredient1() + " " + l_list.get(0).getStrIngredient2();
        temp += " " + l_list.get(0).getStrIngredient3() +" "+ l_list.get(0).getStrIngredient4();

        txt_ingredients.setText(temp);

        Glide.with(this)
                .load(l_list.get(0).getStrMealThumb())
                .apply(new RequestOptions()
                        .override(200, 200)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .into(this.img_meal);

        //txt_ingredients.setText(l_list.get(0).get);

        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFavAddclick(l_list.get(0));
                // holder.btnAddToFav.setBackgroundColor(0x03A9F4FF);
            }
        });
    }

    @Override
    public void onFavAddclick(POJO_class favProduct) {
        myDisplayMealDetailsPresenterImpl.addToFav(favProduct);
    }
}