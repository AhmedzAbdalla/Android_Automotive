package com.example.foodplanner.listingbycategory.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.DisplayFavMeals.View.AdapterOfFavFragment;

import com.example.foodplanner.R;
import com.example.foodplanner.model.Category_Pojo;
import com.example.foodplanner.model.POJO_class;

import java.util.ArrayList;
import java.util.List;

public class AdapterOfCategorylisting
        extends RecyclerView.Adapter<AdapterOfCategorylisting.ViewHolder> {

    //private String[] localDataSet;
    private Context _context;
    private List<Category_Pojo> myDataSet;
    private OnCategoryItemClickListener myListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;

        private TextView CattextBelowImage;
        private ImageView CatimageView;

        public ViewHolder(View view) {
            super(view);
            CattextBelowImage = view.findViewById(R.id.categoryName);
            CatimageView = view.findViewById(R.id.categoryImage);

        }

    }


    public void setList(List<Category_Pojo> myDataSet)
    {
        this.myDataSet = myDataSet;
    }
    public AdapterOfCategorylisting(Context _context , OnCategoryItemClickListener listener) {

        this._context = _context;
        myDataSet = new ArrayList<>();
        this.myListener = listener;
    }

    @NonNull
    @Override
    public AdapterOfCategorylisting.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item, viewGroup, false);
        Log.i("TAG", "zzz");
        Log.i("TAG", "=== onCreateViewHolder ===");

        return new AdapterOfCategorylisting.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterOfCategorylisting.ViewHolder viewHolder, final int position) {
        if(null != myDataSet)
        {
            viewHolder.CattextBelowImage.setText(myDataSet.get(position).getStrCategory());

            Glide.with(_context)
                    .load(myDataSet.get(position).getStrCategoryThumb())
                    .apply(new RequestOptions()
                            .override(200, 200)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(viewHolder.CatimageView);

            // Set a click listener on the item view
            viewHolder.itemView.setOnClickListener(v -> {
                if (myListener != null) {
                    myListener.onCategoryItemClick(myDataSet.get(position));
                }
            });


        }


    }

    @Override
    public int getItemCount() {
        Log.i("TAG", "datasetcount");
        return myDataSet.size();
    }
}
