package com.example.product_app_mvc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkCallback, if_addfavproduct{

    RecyclerView myRecyclerView;
    Fav_Product_DB_DAO dao;
    Button btnAddToFav;

    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);

        repo = Repository.getInstance(this);


        RetrofitClient retrofitClient = RetrofitClient.getInstance(this);

        retrofitClient.fetchAndStoreProducts(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.row), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    @Override
    public void onSuccessResult(List<POJO_class> myproducts) {

        MyAdapter adp = new MyAdapter(this, myproducts,this);

        myRecyclerView.setAdapter(adp);

        //Log.i("RetrofitClient", "onResponse"+myproducts);
    }

    @Override
    public void onFailureResult(String ErrorMsg) {

    }

    @Override
    public void onFavAddclick(POJO_class favProduct) {
        repo.insert(favProduct);
        Toast.makeText(this,"Add" , Toast.LENGTH_SHORT).show();

    }
}