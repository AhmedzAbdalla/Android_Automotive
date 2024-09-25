package com.example.foodplanner.SearchForMeals.Viewer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.DB.MealsLocalDataSourceImpl;
import com.example.foodplanner.DisplayMealDetails.Viewer.MealDetailsActivity;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.SearchForMeals.Presenter.SearchForMealsPresenterImpl;
import com.example.foodplanner.model.MealsRepositoryImpl;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

public class Fragment_Search extends Fragment implements SearchFragmentView, OnItemClickListener {

    RecyclerView myrecyclerView;
    TextView editSearchtxt;
    Button btnSearch;

    SearchForMealsPresenterImpl mySearchForMealsImpl;
    AdapterOfSearchFragment myAdapterOfSearchFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editSearchtxt = view.findViewById(R.id.editSearchtxt);
        btnSearch = view.findViewById(R.id.btnSearch);

        // Initialize RecyclerView
        myrecyclerView = view.findViewById(R.id.recyclerViewSearch);
        myrecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myrecyclerView.setLayoutManager(layoutManager);

        myAdapterOfSearchFragment = new AdapterOfSearchFragment(this.getContext(), this);
        //List<POJO_class> test = new ArrayList<>();
        //
        //POJO_class m1 = new POJO_class();
        //m1.setStrMeal("44");
        //m1.setIdMeal("55");
        //m1.setStrCategory("ewewe");
        //
        //test.add(m1);
        //myAdapterOfSearchFragment.setList(test);
        myrecyclerView.setAdapter(myAdapterOfSearchFragment);

        mySearchForMealsImpl = new SearchForMealsPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(getContext()), MealsLocalDataSourceImpl.getInstance(getContext())));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Arrabiata
                mySearchForMealsImpl.getProductsbyCountry(editSearchtxt.getText().toString()); //Canadian
                //mySearchForMealsImpl.getProductsbyIngredient(editSearchtxt.getText().toString());
                //mySearchForMealsImpl.getProductsbyCategory(editSearchtxt.getText().toString()); //Seafood

            }
        });


        //mySearchForMealsImpl = new SearchForMealsPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(getContext()), MealsLocalDataSourceImpl.getInstance(getContext())));


        //mySearchForMealsImpl.getProducts();
        return view;
        //return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void showProducts(List<POJO_class> l_list) {
        Log.i("TAG", l_list.toString());
        myAdapterOfSearchFragment.setList(l_list);
        Log.i("TAG", "zzzz");
        myAdapterOfSearchFragment.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(POJO_class item) {

        //====================
        // Create an Intent to start MealDetailActivity
        Intent intent = new Intent(getContext(), MealDetailsActivity.class);

        //Gson gson = new Gson();
        //String myJson = gson.toJson(vp);
        //intent.putExtra("myjson", myjson);

        // Pass data (the meal name) to the new activity
        intent.putExtra("meal_ID", item.getIdMeal());

        // Start the new activity
        startActivity(intent);
        //====================

        Log.i("TAG", "onItemClick");
        Toast.makeText(getContext(), "Clicked: " + item.getIdMeal(), Toast.LENGTH_SHORT).show();
    }
}