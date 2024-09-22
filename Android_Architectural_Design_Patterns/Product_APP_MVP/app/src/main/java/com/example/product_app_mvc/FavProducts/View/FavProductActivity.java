package com.example.product_app_mvc.FavProducts.View;

import android.os.Bundle;
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

import com.example.product_app_mvc.DB.ProductsLocalDataSourceImpl;
//import com.example.product_app_mvc.FavProductAdapter;
//import com.example.product_app_mvc.FavProducts.FavProductAdapter;
import com.example.product_app_mvc.FavProducts.Presenter.FavoritePresenter;
import com.example.product_app_mvc.FavProducts.Presenter.FavoritePresenterImpl;
import com.example.product_app_mvc.Network.ProductsRemoteDataSourceImpl;
import com.example.product_app_mvc.R;
import com.example.product_app_mvc.model.POJO_class;
import com.example.product_app_mvc.model.ProductsRepositoryImpl;

import java.util.List;

public class FavProductActivity extends AppCompatActivity implements if_DeleteFavProduct, FavView {

    RecyclerView myRecyclerView;

    LiveData<List<POJO_class>> MyFavProducts;
    ProductsLocalDataSourceImpl repo;

    //===========================
    FavProductAdapter myfavAdapter;
    FavoritePresenter myFavoritePresenter;
    //===========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fav_main);

        //repo = ProductsLocalDataSourceImpl.getInstance(this);
        //MyFavProducts =  repo.getStoredData();

       // Log.i("TAG", MyFavProducts);

        myRecyclerView = findViewById(R.id.fav_myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);


        myFavoritePresenter = new FavoritePresenterImpl(this, ProductsRepositoryImpl.getInstance(ProductsRemoteDataSourceImpl.getInstance(this), ProductsLocalDataSourceImpl.getInstance(this)));

        myfavAdapter = new FavProductAdapter(this , this);

        MyFavProducts = myFavoritePresenter.getAllProducts();

        myRecyclerView.setAdapter(myfavAdapter);

        // Observe LiveData and update adapter
        MyFavProducts.observe(this, new Observer<List<POJO_class>>() {
            @Override
            public void onChanged(List<POJO_class> pojoClasses) {
                myfavAdapter.setadapter(pojoClasses);
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
    public void showProducts(List<POJO_class> l_list) {

    }
}