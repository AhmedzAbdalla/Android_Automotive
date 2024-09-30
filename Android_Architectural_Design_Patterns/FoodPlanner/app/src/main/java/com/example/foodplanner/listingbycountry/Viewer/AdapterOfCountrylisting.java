package com.example.foodplanner.listingbycountry.Viewer;

import android.content.Context;
import android.util.Log;
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
import com.example.foodplanner.listingbycategory.View.OnCategoryItemClickListener;
import com.example.foodplanner.model.Category_Pojo;
import com.example.foodplanner.model.POJO_class;

import java.util.ArrayList;
import java.util.List;

public class AdapterOfCountrylisting
        extends RecyclerView.Adapter<AdapterOfCountrylisting.ViewHolder> {

    //private String[] localDataSet;
    private Context _context;
    private List<POJO_class> myDataSet;
    private OnCountryItemClickListener myOnCountryItemClickListener;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;

        private TextView CountrytextBelowImage;
        private ImageView CatimageView;

        public ViewHolder(View view) {
            super(view);
            CountrytextBelowImage = view.findViewById(R.id.countryName);
            CatimageView = view.findViewById(R.id.categoryImage);

        }

    }


    public void setList(List<POJO_class> l_myDataSet)
    {
        Log.i("TAG", "&&^^%^%");
        Log.i("TAG", l_myDataSet.get(0).getStrArea());

        this.myDataSet = l_myDataSet;
        Log.i("TAG", "$%#$%#$%");
        Log.i("TAG", this.myDataSet.get(0).getStrArea());
    }
    public AdapterOfCountrylisting(Context _context , OnCountryItemClickListener l_listener) {

        this._context = _context;
        this.myDataSet = new ArrayList<>();
        this.myOnCountryItemClickListener = l_listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.country_item, viewGroup, false);
        Log.i("TAG", "zzz");
        Log.i("TAG", "=== onCreateViewHolder ===");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if(null != myDataSet)
        {
            Log.i("TAG", "!!!!!!!!!!!!!!");
            //Log.i("TAG", myDataSet.get(position).toString().);
            viewHolder.CountrytextBelowImage.setText(myDataSet.get(position).getStrArea());
            viewHolder.CatimageView.;
           // Glide.with(_context)
           //         .load(myDataSet.get(position).getStrCategoryThumb())
           //         .apply(new RequestOptions()
           //                 .override(200, 200)
           //                 .placeholder(R.drawable.ic_launcher_foreground)
           //                 .error(R.drawable.ic_launcher_foreground))
           //         .into(viewHolder.CatimageView);

            // Set a click listener on the item view
            viewHolder.itemView.setOnClickListener(v -> {
                if (myOnCountryItemClickListener != null) {
                    myOnCountryItemClickListener.onCountryItemClick(myDataSet.get(position));
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        Log.i("TAG", "datasetcount");
       //Log.i("TAG",(myDataSet.get(0).getStrArea()));

        return myDataSet.size();
    }
}
