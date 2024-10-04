package com.example.foodplanner.SearchForMeals.Viewer;

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
import com.example.foodplanner.R;
import com.example.foodplanner.model.POJO_class;

import java.util.ArrayList;
import java.util.List;

public class AdapterOfSearchFragment extends RecyclerView.Adapter<AdapterOfSearchFragment.ViewHolder> {

    private String[] localDataSet;
    private Context _context;
    private List<POJO_class> myDataSet;
    private OnItemClickListener myOnItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;

        private TextView txt_meal_name;
        public ImageView img;
        public ViewHolder(View view) {
            super(view);
            txt_meal_name = view.findViewById(R.id.txt_meal_name);
            img = view.findViewById(R.id.imageView);
        }

        public void bind(final POJO_class item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);  // Pass the clicked item to the listener
                }
            });
        }

        //public TextView getTextView() {
        //    return textView;
        //}
    }

    //String[] data

    public void setList(List<POJO_class> myDataSet)
    {
        this.myDataSet = myDataSet;
    }
    public AdapterOfSearchFragment(Context _context ,OnItemClickListener l_OnItemClickListener ) {
        //localDataSet = dataSet;
        this._context = _context;
        myDataSet = new ArrayList<>();
        this.myOnItemClickListener = l_OnItemClickListener;
        Log.i("TAG", "www");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_layout_resource, viewGroup, false);
        Log.i("TAG", "zzz");
        Log.i("TAG", "=== onCreateViewHolder ===");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        if(myDataSet!= null)
        {
            viewHolder.txt_meal_name.setText(myDataSet.get(position).getStrMeal());
            Log.i("TAG", "ddd");
            Log.i("TAG", myDataSet.get(position).getStrMeal());
            Glide.with(_context)
                    .load(myDataSet.get(position).getStrMealThumb())
                    .apply(new RequestOptions()
                            .override(200, 200)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(viewHolder.img);
        }


        // Set the click listener for the item

        // Set onClickListener on the itemView
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOnItemClickListener != null) {
                    //Log.d("Adapter", "Item clicked: " + myDataSet.getStrMeal());
                    myOnItemClickListener.onItemClick(myDataSet.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        //Log.i("TAG", "myDataSet.get(1).getStrMeal()");
        //Log.i("TAG", myDataSet.get(1).getStrMeal());
        if(myDataSet!= null)
        {
            return myDataSet.size();
        }
        else
        {
            Toast.makeText(_context.getApplicationContext(), "No Result Found", Toast.LENGTH_SHORT).show();

            return 0;
        }
    }
}
