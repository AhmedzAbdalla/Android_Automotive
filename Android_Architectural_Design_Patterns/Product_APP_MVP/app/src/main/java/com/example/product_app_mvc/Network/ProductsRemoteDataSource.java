package com.example.product_app_mvc.Network;

public interface ProductsRemoteDataSource {

    public void fetchAndStoreProducts(NetworkCallback myNetworkCallback);
}
