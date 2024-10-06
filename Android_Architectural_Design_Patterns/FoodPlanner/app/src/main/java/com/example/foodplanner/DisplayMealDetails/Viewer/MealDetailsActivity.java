package com.example.foodplanner.DisplayMealDetails.Viewer;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.DB.MealsLocalDataSourceImpl;
import com.example.foodplanner.DisplayMealDetails.Presenter.DisplayMealDetailsPresenterImpl;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.Planner.Presenter.PlannerPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.MealsRepositoryImpl;
import com.example.foodplanner.model.POJO_class;
import com.example.foodplanner.model.PlannedMeal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsView , if_AddFavMeal, if_AddPlanMeal{

    private TextView   txt_meal_name;
    private ImageView  img_meal;
    private TextView   txt_origin_country;
    private TextView   txt_steps;
    private TextView   txt_ingredients;
    private WebView video_view;
    private Button     btn_favorite;
    private Button btn_CalenderAdd;
    CalendarView calendarView;

    Date date = new Date();
    String mealType= "";
    //SearchForMealsPresenterImpl mySearchForMealsImpl;

    DisplayMealDetailsPresenterImpl myDisplayMealDetailsPresenterImpl;
    PlannerPresenterImpl myPlannerPresenterImpl;

    private RecyclerView recyclerViewIngredients;

    private IngredientsAdapter adapter;
    private List<String> ingredients;
    private List<String> measures;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_details);

        myPlannerPresenterImpl = new PlannerPresenterImpl(MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(this), MealsLocalDataSourceImpl.getInstance(this)));


        txt_meal_name = findViewById(R.id.txt_meal_name);
        img_meal = findViewById(R.id.img_meal);
        txt_origin_country = findViewById(R.id.txt_origin_country);
        txt_steps = findViewById(R.id.txt_steps);
        //txt_ingredients = findViewById(R.id.txt_ingredients);
        video_view = findViewById(R.id.video_view);
        btn_favorite = findViewById(R.id.btn_delete);
        btn_CalenderAdd = findViewById(R.id.btn_CalenderAdd);
        calendarView = findViewById(R.id.Plancalendar);
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long today = calendar.getTimeInMillis();
        calendarView.setMinDate(today);
        //===
        recyclerViewIngredients = findViewById(R.id.recyclerViewIngredients);
        recyclerViewIngredients.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new GridLayoutManager(this , 1);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewIngredients.setLayoutManager(layoutManager);


        adapter = new IngredientsAdapter(this ,ingredients);

        recyclerViewIngredients.setAdapter(adapter);
        //==
        myDisplayMealDetailsPresenterImpl = new DisplayMealDetailsPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(this), MealsLocalDataSourceImpl.getInstance(this)));


        String code = getIntent().getStringExtra("code");
        if(!(code.equals("test")))
        {
            String mealName = getIntent().getStringExtra("meal_ID");
            //txt_meal_name.setText(mealName);
            myDisplayMealDetailsPresenterImpl.getMealDetails(Integer.valueOf(mealName));
            btn_CalenderAdd.setVisibility(View.VISIBLE);

        }
        else
        {
            btn_CalenderAdd.setVisibility(View.INVISIBLE);
            List<POJO_class> temp = new ArrayList<>();
            temp.add(getIntent().getParcelableExtra("meal"));
            showMealDisplay(temp);
        }

        // Configure WebView settings
        WebSettings webSettings = video_view.getSettings();
        webSettings.setJavaScriptEnabled(true);  // Enable JavaScript for YouTube video playback
        video_view.setWebViewClient(new WebViewClient());  // Ensure the video opens within the WebView

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void showMealDisplay(List<POJO_class> l_list) {
        ingredients = l_list.get(0).getIngredients();
        measures = l_list.get(0).getMeasures();
        adapter.setList(ingredients, measures);

        txt_meal_name.setText(l_list.get(0).getStrMeal());
        txt_origin_country.setText(l_list.get(0).getStrArea());
        txt_steps.setText(l_list.get(0).getStrInstructions());
        Glide.with(this)
                .load(l_list.get(0).getStrMealThumb())
                .apply(new RequestOptions()
                        .override(200, 200)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .into(this.img_meal);

        //txt_ingredients.setText(l_list.get(0).get);


        // Check if a YouTube video URL is provided and display it in WebView
        if (l_list.get(0).getStrYoutube() != null && !l_list.get(0).getStrYoutube().isEmpty()) {
            video_view.setVisibility(View.VISIBLE);

            // Load the YouTube video
            String videoUrl = l_list.get(0).getStrYoutube().replace("watch?v=", "embed/");
            String iframe = "<iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe>";
            video_view.loadData(iframe, "text/html", "utf-8");

        } else {
            video_view.setVisibility(View.GONE);
            Log.i("Video", "displayRandomMeal: No Video Available");
        }


        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFavAddclick(l_list.get(0));
                // holder.btnAddToFav.setBackgroundColor(0x03A9F4FF);
            }
        });

        btn_CalenderAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarDialog(l_list);

            }
        });
    }

    @Override
    public void onFavAddclick(POJO_class favProduct) {
        myDisplayMealDetailsPresenterImpl.addToFav(favProduct);
    }

    @Override
    public void onPlanAddclick(POJO_class favProduct) {
    }

    private void showCalendarDialog(List<POJO_class> l_list) {
        // Create a dialog
        // Create a dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_calendar);  // Updated layout for dialog
        dialog.setTitle("Select a Date and Meal Type");

        // Get views from the dialog layout
        CalendarView calendarView = dialog.findViewById(R.id.calendarView);
        RadioGroup radioGroupMealType = dialog.findViewById(R.id.radioGroupMealType);
        Button btnSave = dialog.findViewById(R.id.btnSave);
        // Set an onDateChangeListener to capture the selected date
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Get the selected date
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth , 0,0,0);
                selectedCalendar.set(Calendar.MILLISECOND,0);
                selectedCalendar.set(Calendar.HOUR_OF_DAY, 0);
                selectedCalendar.set(Calendar.MINUTE, 0);
                selectedCalendar.set(Calendar.SECOND, 0);
                selectedCalendar.set(Calendar.MILLISECOND, 0);

                long today = selectedCalendar.getTimeInMillis();
                calendarView.setMinDate(today);

                date = selectedCalendar.getTime();
                Log.d("Save Date", "Date queried: " + date.toString());


                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(getBaseContext(), "Selected Date: " + date, Toast.LENGTH_SHORT).show();
                //dialog.dismiss();  // Close the dialog when a date is selected
            }
        });

        // Set up the save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected meal type
                int selectedMealId = radioGroupMealType.getCheckedRadioButtonId();
                RadioButton selectedMealButton = dialog.findViewById(selectedMealId);
                mealType = selectedMealButton != null ? selectedMealButton.getText().toString() : "Not Selected";

                // Get the selected date
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                Date selectedDate = calendar.getTime();


                PlannedMeal m1 = new PlannedMeal(l_list.get(0),date,l_list.get(0).getIdMeal(),mealType);
                myPlannerPresenterImpl.AddtoPlannedTable(m1,date);
                // Dismiss the dialog after selecting the date
                dialog.dismiss();
            }
        });




        // Show the dialog
        dialog.show();
    }

}