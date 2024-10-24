package com.example.foodplanner.DisplayMealDetails.Viewer;

import static java.security.AccessController.getContext;

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
import com.example.foodplanner.NetworkUtils;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    List<String> ingredients;
    List<String> measures;
    Context _context;

    public IngredientsAdapter(Context _context,List<String> ingredients) {
        this._context = _context;
        this.ingredients = new ArrayList<>();
        this.measures = new ArrayList<>();
    }

    public void setList(List<String> ingredients, List<String> measures) {
        //this.ingredients.clear();

        this.ingredients.addAll(ingredients);
        this.measures.addAll(measures);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;

        private TextView txt_ingredient_name;
        private TextView txt_ingredient_measure;
        private ImageView img_ingredient;

        public ViewHolder(View view) {
            super(view);
            txt_ingredient_name = view.findViewById(R.id.txt_ingredient_name);
            img_ingredient = view.findViewById(R.id.img_ingredient);
            txt_ingredient_measure = view.findViewById(R.id.txt_ingredient_measure);

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new IngredientsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        String ingredient = ingredients.get(position);

        Log.i("TAG", "#@$#@$#@$@##$@$%");
        String measure = measures.get(position);
        holder.txt_ingredient_name.setText(ingredient);
        holder.txt_ingredient_measure.setText(measure);

        if (NetworkUtils.isInternetAvailable(_context.getApplicationContext())) {
            // If internet is available, make network calls
            Glide.with(holder.itemView.getContext()).load("https://www.themealdb.com/images/ingredients/" + ingredient + ".png").into(holder.img_ingredient);

        } else {
            // No internet connection, show a message to the user
            Toast.makeText(_context.getApplicationContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}