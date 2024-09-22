package com.example.product_app_mvc.AllProducts.Viewer.Viewer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.product_app_mvc.AllProducts.Presenter.AllProductsPresenterImpl;
import com.example.product_app_mvc.DB.ProductsLocalDataSourceImpl;
import com.example.product_app_mvc.Network.ProductsRemoteDataSourceImpl;
import com.example.product_app_mvc.model.POJO_class;
import com.example.product_app_mvc.R;
import com.example.product_app_mvc.model.ProductsRepositoryImpl;

import java.util.List;

public class AllProductActivity extends AppCompatActivity implements if_AddFavProduct, AllProductView {

    RecyclerView myRecyclerView;

    //=======================

    AllProductsPresenterImpl myProductPresenter;
    AllProductAdapter myProductAdapter;;
    //=======================


    ProductsLocalDataSourceImpl repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //==========================
        myProductAdapter = new AllProductAdapter(this, this);
        myProductPresenter = new AllProductsPresenterImpl(this, ProductsRepositoryImpl.getInstance(ProductsRemoteDataSourceImpl.getInstance(this), ProductsLocalDataSourceImpl.getInstance(this)));
        //==========================

        myRecyclerView = findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(myProductAdapter);

        myProductPresenter.getProducts();

        //repo = ProductsLocalDataSourceImpl.getInstance(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.row), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }


    @Override
    public void onFavAddclick(POJO_class favProduct) {
        myProductPresenter.addToFav(favProduct);
        Toast.makeText(this,"Add" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProducts(List<POJO_class> l_list) {
        myProductAdapter.setList(l_list);
        Log.i("TAG", "zzzz");
        myProductAdapter.notifyDataSetChanged();
    }
}