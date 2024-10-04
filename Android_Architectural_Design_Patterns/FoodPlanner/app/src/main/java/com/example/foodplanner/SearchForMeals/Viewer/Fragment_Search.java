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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.DB.MealsLocalDataSourceImpl;
import com.example.foodplanner.DisplayMealDetails.Viewer.MealDetailsActivity;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.NetworkUtils;
import com.example.foodplanner.R;
import com.example.foodplanner.SearchForMeals.Presenter.SearchForMealsPresenterImpl;
import com.example.foodplanner.model.MealsRepositoryImpl;
import com.example.foodplanner.model.POJO_class;

import java.util.List;

public class Fragment_Search extends Fragment implements SearchFragmentView, OnItemClickListener {

    RecyclerView myrecyclerView;
    TextView editSearchtxt;
    Button btnSearch;
    Spinner searchTypeSpinner;
    String selectedSearchType;

    SearchForMealsPresenterImpl mySearchForMealsImpl;
    AdapterOfSearchFragment myAdapterOfSearchFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editSearchtxt = view.findViewById(R.id.editSearchtxt);
        btnSearch = view.findViewById(R.id.btnSearch);
        searchTypeSpinner = view.findViewById(R.id.spinner_search_type);
        // Setup spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.search_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(adapter);

        // Listen for spinner selection changes
        searchTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedSearchType = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        // Initialize RecyclerView
        myrecyclerView = view.findViewById(R.id.recyclerViewSearch);
        myrecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myrecyclerView.setLayoutManager(layoutManager);

        myAdapterOfSearchFragment = new AdapterOfSearchFragment(this.getContext(), this);

        myrecyclerView.setAdapter(myAdapterOfSearchFragment);

        mySearchForMealsImpl = new SearchForMealsPresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(getContext()), MealsLocalDataSourceImpl.getInstance(getContext())));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Arrabiata

                // Check for internet connection before making any network requests
                if (NetworkUtils.isInternetAvailable(getContext())) {
                    // If internet is available, make network calls
                    performSearch();
                } else {
                    // No internet connection, show a message to the user
                    Toast.makeText(getContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }

                //mySearchForMealsImpl.getProductsbyCountry(editSearchtxt.getText().toString()); //Canadian
                //mySearchForMealsImpl.getProductsbyIngredient(editSearchtxt.getText().toString());
                //mySearchForMealsImpl.getProductsbyCategory(editSearchtxt.getText().toString()); //Seafood

            }
        });

        return view;
      }

    private void performSearch() {
        String query = editSearchtxt.getText().toString();
        switch (selectedSearchType) {
            case "Category":
                mySearchForMealsImpl.getProductsbyCategory(query);
                break;
            case "Country":
                mySearchForMealsImpl.getProductsbyCountry(query);
                break;
            case "Ingredient":
                mySearchForMealsImpl.getProductsbyIngredient(query);
                break;
            default:
                Toast.makeText(getContext(), "Please select a search type", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void showProducts(List<POJO_class> l_list) {
        //Log.i("TAG", l_list.toString());
        myAdapterOfSearchFragment.setList(l_list);
        Log.i("TAG", "zzzz");
        myAdapterOfSearchFragment.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(POJO_class item) {

        //====================
        // Create an Intent to start MealDetailActivity
        Intent intent = new Intent(getContext(), MealDetailsActivity.class);

        // Pass data (the meal name) to the new activity
        intent.putExtra("meal_ID", item.getIdMeal());
        intent.putExtra("code", "ntest");

        // Start the new activity
        startActivity(intent);
        //====================

        Log.i("TAG", "onItemClick");
        Toast.makeText(getContext(), "Clicked: " + item.getIdMeal(), Toast.LENGTH_SHORT).show();
    }
}