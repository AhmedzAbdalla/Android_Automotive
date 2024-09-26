package com.example.foodplanner.DisplayFavMeals.View;

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
import com.example.foodplanner.R;
import com.example.foodplanner.SearchForMeals.Viewer.OnItemClickListener;
import com.example.foodplanner.model.POJO_class;

import java.util.ArrayList;
import java.util.List;

public class AdapterOfFavFragment extends RecyclerView.Adapter<AdapterOfFavFragment.ViewHolder> {

    //private String[] localDataSet;
    private Context _context;
    private List<POJO_class> myDataSet;
    if_DeleteFavProduct myif_DeleteFavProduct;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;

        private TextView   txt_meal_name;
        private ImageView  img_meal;
        private TextView   txt_origin_country;
        private TextView   txt_steps;
        private TextView   txt_ingredients;
        private VideoView video_view;
        private Button btn_favorite;
        public ViewHolder(View view) {
            super(view);
            txt_meal_name = view.findViewById(R.id.txt_meal_name);
            img_meal = view.findViewById(R.id.img_meal);
            txt_origin_country = view.findViewById(R.id.txt_origin_country);
            txt_steps = view.findViewById(R.id.txt_steps);
            txt_ingredients = view.findViewById(R.id.txt_ingredients);
            video_view = view.findViewById(R.id.video_view);
            btn_favorite = view.findViewById(R.id.btn_delete);
        }

    }

    //String[] data

    public void setList(List<POJO_class> myDataSet)
    {
        this.myDataSet = myDataSet;
    }
    public AdapterOfFavFragment(Context _context, if_DeleteFavProduct _myif_DeleteFavProduct) {
        //localDataSet = dataSet;
        this._context = _context;
        myDataSet = new ArrayList<>();
        this.myif_DeleteFavProduct =_myif_DeleteFavProduct;
        Log.i("TAG", "www");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.displayfavmealsresource, viewGroup, false);
        Log.i("TAG", "zzz");
        Log.i("TAG", "=== onCreateViewHolder ===");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.txt_meal_name.setText(myDataSet.get(0).getStrMeal());
        viewHolder.txt_origin_country.setText(myDataSet.get(0).getStrArea());
        viewHolder.txt_steps.setText(myDataSet.get(0).getStrInstructions());
        String temp = myDataSet.get(0).getStrIngredient1() + " " + myDataSet.get(0).getStrIngredient2();
        temp += " " + myDataSet.get(0).getStrIngredient3() +" "+ myDataSet.get(0).getStrIngredient4();

        viewHolder.txt_ingredients.setText(temp);
       Glide.with(_context)
               .load(myDataSet.get(position).getStrMealThumb())
               .apply(new RequestOptions()
                       .override(200, 200)
                       .placeholder(R.drawable.ic_launcher_foreground)
                       .error(R.drawable.ic_launcher_foreground))
               .into(viewHolder.img_meal);

        viewHolder.btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myif_DeleteFavProduct.onFavdeleteclick(myDataSet.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("TAG", "datasetcount");
        return myDataSet.size();
    }
}
