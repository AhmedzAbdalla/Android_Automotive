package com.example.foodplanner.SearchForMeals.Viewer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.POJO_class;

import java.util.ArrayList;
import java.util.List;

public class AdapterOfSearchFragment extends RecyclerView.Adapter<AdapterOfSearchFragment.ViewHolder> {

    private String[] localDataSet;
    private Context _context;
    private List<POJO_class> myDataSet;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;

        private TextView txt_meal_name;
        public ViewHolder(View view) {
            super(view);
            txt_meal_name = view.findViewById(R.id.txt_meal_name);
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
    public AdapterOfSearchFragment(Context _context) {
        //localDataSet = dataSet;
        this._context = _context;
        myDataSet = new ArrayList<>();
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
        viewHolder.txt_meal_name.setText(myDataSet.get(position).getStrMeal());
        Log.i("TAG", "ddd");
        Log.i("TAG", myDataSet.get(position).getStrMeal());

    }

    @Override
    public int getItemCount() {
        Log.i("TAG", "datasetcount");
        return myDataSet.size();
    }
}
