package com.example.foodplanner.Planner.Viewer;
import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.NetworkUtils;
import com.example.foodplanner.R;
import com.example.foodplanner.model.PlannedMeal;
//import com.example.foodplanner.favourite.view.FavOnClickListener;
//import com.example.foodplanner.model.PlanedMeal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder>{
    private List<PlannedMeal> plannedMeals;
    private Context context;
    private onPlanClickListener onClick;


    private static final String TAG ="l";
    public PlanAdapter(List<PlannedMeal> plannedMeals, Context context , onPlanClickListener onClick) { //
        this.plannedMeals = new ArrayList<>();
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Fetch the current planned meal
        if(null != plannedMeals)
        {
            PlannedMeal meal = plannedMeals.get(position);
//
            holder.mealName.setText(meal.getMeal().getStrMeal());

            // Format date and display it
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            holder.mealDate.setText(dateFormat.format(meal.getDate()));

            // Display meal type (e.g., breakfast, lunch, dinner)
            holder.mealType.setText(meal.getMealType());

            // Display meal area (e.g., area of origin)
            holder.mealArea.setText(meal.getMeal().getStrArea());

            if (NetworkUtils.isInternetAvailable(context.getApplicationContext())) {
                // If internet is available, make network calls
                // Load meal image using Glide
                Glide.with(context)
                        .load(meal.getMeal().getStrMealThumb())
                        .apply(new RequestOptions().override(500, 500).placeholder(R.drawable.ic_launcher_foreground))
                        .into(holder.mealImg);
            } else {
                // No internet connection, show a message to the user
                Toast.makeText(context.getApplicationContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
            }


        }



        // Handle delete click
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClick.onDelClicked(plannedMeals.get(position));

                plannedMeals.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, plannedMeals.size());
                //onClick.onDelClicked(meal);
            }
        });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onMealItemClicked(plannedMeals.get(position));
                    Log.i(TAG, "#@#@@#");
                }
            });


        // Handle item click

    }

    @Override
    public int getItemCount() {
        return plannedMeals.size();
    }

    public List<PlannedMeal> getPlannedMeals() {
        return plannedMeals;
    }
    public static class PlanViewHolder extends RecyclerView.ViewHolder {
        TextView mealName;
        TextView mealDate;
        TextView mealArea;
        TextView mealType;
        ImageView btnDelete;
        ImageView mealImg;
        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.planmealName);
            mealDate = itemView.findViewById(R.id.planmealDate);
            mealArea = itemView.findViewById(R.id.planmealArea);
            mealType = itemView.findViewById(R.id.planType);
            mealImg = itemView.findViewById(R.id.planmealImg);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setlist(List<PlannedMeal> newMealPlans) {
        Log.i(TAG, "updateMeals: ");
        this.plannedMeals.clear();
        this.plannedMeals.addAll(newMealPlans);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clearMeals() {
        Log.i(TAG, "clearMeals in adapter: ");
        this.plannedMeals = new ArrayList<>();
        notifyDataSetChanged();
    }
}
