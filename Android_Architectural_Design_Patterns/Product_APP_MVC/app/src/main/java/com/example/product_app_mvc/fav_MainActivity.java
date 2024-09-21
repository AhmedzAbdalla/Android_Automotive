package com.example.product_app_mvc;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class fav_MainActivity extends AppCompatActivity implements if_deletefavproduct {

    RecyclerView myRecyclerView;

    LiveData<List<POJO_class>> MyFavProducts;
    Repository repo;
    FavAdapter favAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fav_main);

        repo = Repository.getInstance(this);
        MyFavProducts =  repo.getStoredData();

       // Log.i("TAG", MyFavProducts);

        myRecyclerView = findViewById(R.id.fav_myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);


        favAdapter = new FavAdapter(this , this);
        myRecyclerView.setAdapter(favAdapter);

        // Observe LiveData and update adapter
        MyFavProducts.observe(this, new Observer<List<POJO_class>>() {
            @Override
            public void onChanged(List<POJO_class> pojoClasses) {
                favAdapter.setadapter(pojoClasses); // Use correct method name here
            }
        });






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fav_row), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onFavdeleteclick(POJO_class favProduct) {
        repo.delete(favProduct);
        Toast.makeText(this,"Deleted" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getRecordCount() {
        return 0;//repo.getProductCount();
    }
}