package com.example.foodplanner.DisplayFavMeals.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.DisplayMealDetails.Viewer.IngredientsAdapter;
import com.example.foodplanner.NetworkUtils;
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
    public static WebView video_view2;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;

        private TextView   txt_meal_name;
        private ImageView  img_meal;
        private TextView   txt_origin_country;
        private TextView   txt_steps;
        private TextView   txt_ingredients;

        private Button btn_favorite;

        private RecyclerView recyclerViewIngredients2;

        public ViewHolder(View view) {
            super(view);
            txt_meal_name = view.findViewById(R.id.txt_meal_name);
            img_meal = view.findViewById(R.id.img_meal);
            txt_origin_country = view.findViewById(R.id.txt_origin_country);
            txt_steps = view.findViewById(R.id.txt_steps);
            //txt_ingredients = view.findViewById(R.id.txt_ingredients);
            video_view2 = view.findViewById(R.id.video_view2);
            btn_favorite = view.findViewById(R.id.btn_delete);

            recyclerViewIngredients2 = view.findViewById(R.id.recyclerViewIngredients2);  // Child RecyclerView

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
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        viewHolder.txt_meal_name.setText(myDataSet.get(0).getStrMeal());
        viewHolder.txt_origin_country.setText(myDataSet.get(0).getStrArea());
        viewHolder.txt_steps.setText(myDataSet.get(0).getStrInstructions());
        if (NetworkUtils.isInternetAvailable(_context.getApplicationContext())) {
            // If internet is available, make network calls
            Glide.with(_context)
                    .load(myDataSet.get(position).getStrMealThumb())
                    .apply(new RequestOptions()
                            .override(200, 200)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(viewHolder.img_meal);
        } else {
            // No internet connection, show a message to the user
            Toast.makeText(_context.getApplicationContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
        }
        // Configure WebView settings
        WebSettings webSettings = video_view2.getSettings();
        webSettings.setJavaScriptEnabled(true);  // Enable JavaScript for YouTube video playback
        video_view2.setWebViewClient(new WebViewClient());
        // Check if a YouTube video URL is provided and display it in WebView
        if (myDataSet.get(0).getStrYoutube() != null && !myDataSet.get(0).getStrYoutube().isEmpty()) {
            video_view2.setVisibility(View.VISIBLE);

            // Load the YouTube video
            String videoUrl = myDataSet.get(0).getStrYoutube().replace("watch?v=", "embed/");
            String iframe = "<iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe>";
            video_view2.loadData(iframe, "text/html", "utf-8");

        } else {
            video_view2.setVisibility(View.GONE);
            Log.i("Video", "displayRandomMeal: No Video Available");
        }



        //===========
        // Set up the child RecyclerView (for ingredients)
        List<String> ingredients = new ArrayList<>();
        List<String> measures = new ArrayList<>();
        ingredients = myDataSet.get(0).getIngredients();
        measures = myDataSet.get(0).getMeasures();

        // Initialize the child adapter with the ingredients and measures
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(_context, ingredients);
        ingredientsAdapter.setList(ingredients, measures);
        // Set up the layout manager for the child RecyclerView
        LinearLayoutManager childLayoutManager = new LinearLayoutManager(
                viewHolder.recyclerViewIngredients2.getContext(),
                RecyclerView.HORIZONTAL, false); // Horizontal scrolling for ingredients

        viewHolder.recyclerViewIngredients2.setLayoutManager(childLayoutManager);
        viewHolder.recyclerViewIngredients2.setAdapter(ingredientsAdapter);


        // ==========
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
