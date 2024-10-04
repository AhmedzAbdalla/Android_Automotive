package com.example.foodplanner.Planner.Viewer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.foodplanner.DB.MealsLocalDataSourceImpl;
import com.example.foodplanner.DisplayMealDetails.Viewer.MealDetailsActivity;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.Planner.Presenter.PlannerPresenter;
import com.example.foodplanner.Planner.Presenter.PlannerPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.MealsRepository;
import com.example.foodplanner.model.MealsRepositoryImpl;
import com.example.foodplanner.model.POJO_class;
import com.example.foodplanner.model.PlannedMeal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Fragment_Plan extends Fragment implements PlannerView, onPlanClickListener{

    private RecyclerView plannedRecycler;
    private RecyclerView.LayoutManager planLayoutManager;
    private PlanAdapter planAdapter;
    private CalendarView calendarView;
    //private APIClient client;
    //private LocalSource localSource;
    private PlannerPresenter myPlannerPresenter;
    private MealsRepository repo;
    private LiveData<List<PlannedMeal>> plannedMeals;
    private List<PlannedMeal> plannedMeals2;
    //private Date selectedDate;
    Date date = new Date();
    public Fragment_Plan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__plan, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        plannedRecycler = view.findViewById(R.id.planrecycler);
        planLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL , false);
        plannedRecycler.setLayoutManager(planLayoutManager);
        calendarView = view.findViewById(R.id.Plancalendar);
        plannedRecycler.setLayoutManager(new LinearLayoutManager(getContext()));


        myPlannerPresenter = new PlannerPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(getContext()), MealsLocalDataSourceImpl.getInstance(getContext())));
        planAdapter = new PlanAdapter(plannedMeals2, getContext(),this);

        plannedRecycler.setAdapter(planAdapter);

        // Get the current date from CalendarView
        long currentDateInMillis = calendarView.getDate();
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.setTimeInMillis(currentDateInMillis);

        // Clear time from the date to focus only on year, month, and day
        selectedCalendar.set(Calendar.HOUR_OF_DAY, 0);
        selectedCalendar.set(Calendar.MINUTE, 0);
        selectedCalendar.set(Calendar.SECOND, 0);
        selectedCalendar.set(Calendar.MILLISECOND, 0);

        // Get the current date with time cleared
        Date currentDate = selectedCalendar.getTime();
        Log.d("Query Date", "Loading meals for date: " + currentDate.toString());

        // Load planned meals for the current date
        plannedMeals = myPlannerPresenter.getPlannedMealByDate(currentDate);

        plannedMeals.observe(getViewLifecycleOwner(), new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> meals) {
                // Update your adapter with the meal list
                planAdapter.setlist(meals);
                planAdapter.notifyDataSetChanged();
                if (!meals.isEmpty()) {
                    Log.i("TAG", "Meals loaded successfully for the current date.");
                } else {
                    Log.i("TAG", "No meals found for the current date.");
                }
            }
        });

        // selectedDate = new Date(calendarView.getDate());

        // Set a listener to fetch meals based on the selected date
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth , 0,0,0);
                selectedCalendar.set(Calendar.MILLISECOND,0);
                selectedCalendar.set(Calendar.HOUR_OF_DAY, 0);
                selectedCalendar.set(Calendar.MINUTE, 0);
                selectedCalendar.set(Calendar.SECOND, 0);
                selectedCalendar.set(Calendar.MILLISECOND, 0);

                date = selectedCalendar.getTime();
                Log.d("Query Date", "Date queried: " + date.toString());


                String selectedDate2 = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(getContext(), "Selected Date: " + date, Toast.LENGTH_SHORT).show();

                // Load planned meals for the newly selected date
                plannedMeals = myPlannerPresenter.getPlannedMealByDate(date);

                plannedMeals.observe(getViewLifecycleOwner(), new Observer<List<PlannedMeal>>() {
                    @Override
                    public void onChanged(List<PlannedMeal> meals) {

                        planAdapter.setlist(meals);
                        planAdapter.notifyDataSetChanged();
                        Log.i("TAG", "232342");
                        //Log.i("TAG", meals.get(0).getMeal().toString());
                    }
                });

            }
        });


    }

    @Override
    public void showMeals(List<PlannedMeal> meals) {
        planAdapter.setlist(meals);
        planAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErr(String error) {

    }

    @Override
    public void showDatemeal(List<PlannedMeal> meals) {

    }

    @Override
    public void onDelClicked(PlannedMeal meal) {
        planAdapter.notifyDataSetChanged();
        myPlannerPresenter.removeFromPlannedTable(meal);
    }

    @Override
    public void onMealItemClicked(PlannedMeal l_meal) {
        Intent intent = new Intent(getContext(), MealDetailsActivity.class);

        // Pass the meal object
        intent.putExtra("meal", l_meal.getMeal());
        intent.putExtra("code", "test");

        // Start the MealDetailActivity
        getContext().startActivity(intent);
    }
}