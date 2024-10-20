package com.example.kotlinapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.example.myworkmanager.MyWorker
import com.example.myworkmanager.Product
import com.example.myworkmanager.ProductDao
import com.example.myworkmanager.ProductDataBase
import com.example.myworkmanager.ProductResponse
import com.example.myworkmanager.ProductService
import com.example.myworkmanager.R
import com.example.myworkmanager.RetrofitHelper
import com.example.myworkmanager.databinding.ActivityMainBinding
import com.example.myworkmanager.databinding.FragmentABinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class FragmentA : Fragment() ,OnItemClickListener{


    lateinit var myRV : RecyclerView
    lateinit var myRVAdapter : RVAdapter
    private lateinit var comm: FragmentB2.Communicator
    lateinit var productList:ProductResponse
    lateinit var binding: FragmentABinding

    val myColorDao : ProductDao by lazy {
        ProductDataBase.getInstance(requireContext()).getProductDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //=====================
        //outState.putSerializable("test", productList.get(0))
        //=====================

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //Step 1 for fragment dynamic binding
        binding = FragmentABinding.inflate(inflater, container, false)
        //Step 2 for fragment dynamic binding
        val view = binding.root
        //return inflater.inflate(R.layout.fragment_a, container, false)
        //Step 3 for fragment dynamic binding
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //====================

        //====================


        comm = activity as FragmentB2.Communicator

        //Step/s 4 for fragment dynamic binding
        myRV = binding.RV1
        //myRVAdapter( = RVAdapter())
        myRVAdapter = RVAdapter(this, this)
        myRV.adapter = myRVAdapter
        val myLayoutManager  = LinearLayoutManager(this.getContext())
        myRV.layoutManager = myLayoutManager

        //lifecycleScope.launch (Dispatchers.IO ) {
        //    val service = RetrofitHelper.getApiService()
        //    val pproductList = service.fetchProducts2()
        //   // val test:MutableList<ProductResponse> =productList
        //    if (null != pproductList)
        //    {
        //        withContext(Dispatchers.Main){ //To work on Main Thread
        //    //update the UI with your list
        //            myRVAdapter.submitList(pproductList.products)
        //            displayProductData(pproductList.products)
        //        }
        //    }
//
        //}
        lifecycleScope.launch (Dispatchers.IO ) {
            val service = RetrofitHelper.getApiService()
            val pproductList = service.fetchProducts3()
            // val test:MutableList<ProductResponse> =productList
            if (pproductList.isSuccessful)
            {
                withContext(Dispatchers.Main){ //To work on Main Thread
//update the UI with your list
                    myRVAdapter.submitList(pproductList.body()?.products)
                    pproductList.body()?.products?.let { displayProductData(it) }
                }
            }

        }






    }
    companion object {
      @JvmStatic fun newInstance(param1: String, param2: String) =
                FragmentA().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun onItemClicked(productList: List<Product>) {

        //Log.i("tag", productList.get(0).sku)
        lifecycleScope.launch(Dispatchers.IO) {
            myColorDao.insertProduct(productList.get(0))
            withContext(Dispatchers.Main) {

            }
        }
        comm.respond(productList)
        Log.i("TAG", "I'm here")
    }

    private fun displayProductData(products: List<Product>) {
        // Loop through the products and access individual attributes
        products.forEach { product ->
            // Access each attribute individually
            val id = product.id
            val title = product.title
            val description = product.description
            val price = product.price
            val thumbnail = product.thumbnail

            myRVAdapter.submitList(products)

            Log.i("TAG", "displayProductData: $id")
            Log.i("TAG", "displayProductData: $title")
            Log.i("TAG", "displayProductData: $description")
            Log.i("TAG", "displayProductData: $price")
            Log.i("TAG", "displayProductData: $thumbnail")
            //Log.i("TAG", "displayProductData: ${product.sku}")
            //Log.i("TAG", "displayProductData: ${product.brand}")
            //Log.i("TAG", "displayProductData: ${product.discountPercentage}")


            // Example: Displaying the attributes in the UI or logging
            println("ID: $id, Title: $title, Description: $description, Price: $price, Thumbnail: $thumbnail")
            // Here you can update your UI, e.g., populate a RecyclerView or TextViews
        }
    }
}

