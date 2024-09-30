package com.example.foodplanner.listingbycategory.View;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.SearchForMeals.Viewer.OnItemClickListener;
import com.example.foodplanner.model.POJO_class;

import java.util.ArrayList;
import java.util.List;

public class Adapter_item_meal_for_listing extends RecyclerView.Adapter<Adapter_item_meal_for_listing.ViewHolder> {

    private List<POJO_class> myDataset;
    private Context context;
    private CategoryMealonItemClickListener myOnCategoryItemClickListener;

    public Adapter_item_meal_for_listing(Context _context , CategoryMealonItemClickListener l_OnCategoryItemClickListener) {
        this.myDataset = new ArrayList<>();
        this.context = _context;
        this.myOnCategoryItemClickListener = l_OnCategoryItemClickListener;
    }

    public void setList(List<POJO_class> myDataSet)
    {
        this.myDataset = myDataSet;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;

        private TextView meal_name;
        private ImageView meal_image;

        public ViewHolder(View view) {
            super(view);
            meal_name = view.findViewById(R.id.meal_name);
            meal_image = view.findViewById(R.id.meal_image);

        }

    }

    @NonNull
    @Override
    public Adapter_item_meal_for_listing.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal_for_listing, parent, false);
        return new Adapter_item_meal_for_listing.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_item_meal_for_listing.ViewHolder holder, int position) {

        holder.meal_name.setText(myDataset.get(position).getStrMeal());

        Glide.with(context)
                .load(myDataset.get(position).getStrMealThumb())
                .apply(new RequestOptions()
                        .override(200, 200)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.meal_image);

        // Set onClickListener on the itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOnCategoryItemClickListener != null) {
                    //Log.d("Adapter", "Item clicked: " + myDataSet.getStrMeal());
                    myOnCategoryItemClickListener.CategoryMealonItemClickListener(myDataset.get(position));
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return myDataset.size();
    }

}

