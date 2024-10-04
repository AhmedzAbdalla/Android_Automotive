package com.example.foodplanner.DisplayFavMeals.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.foodplanner.DisplayFavMeals.Presenter.FavoritePresenterImpl;
import com.example.foodplanner.DisplayMealDetails.Viewer.IngredientsAdapter;
import com.example.foodplanner.Network.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.SearchForMeals.Presenter.SearchForMealsPresenterImpl;
import com.example.foodplanner.SearchForMeals.Viewer.AdapterOfSearchFragment;
import com.example.foodplanner.SearchForMeals.Viewer.OnItemClickListener;
import com.example.foodplanner.model.MealsRepositoryImpl;
import com.example.foodplanner.model.POJO_class;

import java.util.List;


public class Fragment_Favorite extends Fragment implements FavView, if_DeleteFavProduct {


        RecyclerView myrecyclerView;
        LiveData<List<POJO_class>> MyFavMeals;

        FavoritePresenterImpl myFavoritePresenterImpl;
        AdapterOfFavFragment myAdapterOfFavFragment;


        @SuppressLint("MissingInflatedId")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment__favorite, container, false);

            // Initialize RecyclerView
            myrecyclerView = view.findViewById(R.id.recyclerViewSearch);
            myrecyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            myrecyclerView.setLayoutManager(layoutManager);

            myAdapterOfFavFragment = new AdapterOfFavFragment(this.getContext(), this);
            myrecyclerView.setAdapter(myAdapterOfFavFragment);

            myFavoritePresenterImpl = new FavoritePresenterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance(getContext()), MealsLocalDataSourceImpl.getInstance(getContext())));
            MyFavMeals = myFavoritePresenterImpl.getAllProducts();



            //Toast.makeText(this,"Add" , Toast.LENGTH_SHORT).show();;
            // Observe LiveData and update adapter
            MyFavMeals.observe(getViewLifecycleOwner(), new Observer<List<POJO_class>>() {
                @Override
                public void onChanged(List<POJO_class> pojoClasses) {

                    myAdapterOfFavFragment.setList(pojoClasses);
                    myAdapterOfFavFragment.notifyDataSetChanged();
                    //Log.i("TAG", pojoClasses.get(0).getStrMeal());
                }
            });


            return view ;//inflater.inflate(R.layout.fragment__favorite, container, false);
        }

        @Override
        public void showProducts(List<POJO_class> l_list) {

            myAdapterOfFavFragment.setList(l_list);
            Log.i("TAG", "zzzz");
            myAdapterOfFavFragment.notifyDataSetChanged();
        }

    @Override
    public void onFavdeleteclick(POJO_class favProduct) {
        //repo.delete(favProduct);
        myFavoritePresenterImpl.removeFromFav(favProduct);
        Toast.makeText(this.getContext(),"Deleted" , Toast.LENGTH_SHORT).show();

    }
}