package com.example.kotlinapp

import Product
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.example.myworkmanager.MyWorker
import com.example.myworkmanager.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.TimeUnit

class FragmentA : Fragment() ,OnItemClickListener{


    lateinit var myRV : RecyclerView
    lateinit var myRVAdapter : RVAdapter
    private lateinit var comm: FragmentB2.Communicator
/*
    val productList = listOf(
        Product(
            id = 1,
            title = "Essence Mascara Lash Princess",
            description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects.",
            price = 9.99,
           thumbnail = "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png"
        ),
        Product(
            id = 2,
            title = "Maybelline Fit Me Foundation",
            description = "A lightweight foundation that provides a natural finish.",
            price = 12.99,
           thumbnail = "https://example.com/images/foundation_thumbnail.png"
        ),
        Product(
            id = 3,
            title = "L'Oreal Paris Hair Color",
            description = "Long-lasting hair color with a rich and vibrant finish.",
           price = 8.49,
           thumbnail = "https://example.com/images/hair_color_thumbnail.png"
        ),
        Product(
            id = 4,
            title = "Revlon Super Lustrous Lipstick",
            description = "A highly pigmented lipstick that glides on smoothly.",
            price = 7.99,
           thumbnail = "https://example.com/images/lipstick_thumbnail.png"
        ),
        Product(
            id = 5,
            title = "NYX Professional Makeup Eyeshadow Palette",
            description = "A versatile eyeshadow palette with a mix of matte and shimmer shades.",
          price = 15.00,
           thumbnail = "https://example.com/images/eyeshadow_thumbnail.png"
        ),
        Product(
            id = 6,
            title = "Clinique Moisture Surge",
            description = "A refreshing gel-cream that provides instant hydration.",
            price = 38.00,
            thumbnail = "https://example.com/images/moisture_surge_thumbnail.png"
        ),
        Product(
            id = 7,
            title = "CeraVe Hydrating Facial Cleanser",
            description = "A gentle cleanser that hydrates while removing dirt and makeup.",
            price = 12.00,
           thumbnail = "https://example.com/images/cleanser_thumbnail.png"
        ),
        Product(
            id = 8,
            title = "Nivea Soft Moisturizing Cream",
            description = "A light, refreshing cream for face, body, and hands.",
           price = 5.99,
           thumbnail = "https://example.com/images/nivea_thumbnail.png"
        ),
        Product(
            id = 9,
            title = "Aveeno Daily Moisturizing Lotion",
            description = "A daily lotion that hydrates and protects dry skin.",
            price = 10.50,
         thumbnail = "https://example.com/images/aveeno_thumbnail.png"
        ),
        Product(
            id = 10,
            title = "Garnier SkinActive Micellar Cleansing Water",
            description = "A cleansing water that removes makeup and impurities.",
           price = 8.00,
             thumbnail = "https://example.com/images/micellar_thumbnail.png"
        )
    )
*/
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

        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //====================
        // Create the WorkRequest for ImageDownloadWorker
        val downloadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInputData(workDataOf("code1" to "hi", "code2" to "Hello"))
            //retry policy
            .setBackoffCriteria(BackoffPolicy.LINEAR,1L, TimeUnit.MINUTES)
            .build()
        // Enqueue the WorkRequest
        this.context?.let { WorkManager.getInstance(it).enqueue(downloadWorkRequest) }

        // Observe the work status and progress
        this.context?.let {
            WorkManager.getInstance(it).getWorkInfoByIdLiveData(downloadWorkRequest.id)
                .observe(this.viewLifecycleOwner, Observer { workInfo ->
                    workInfo?.let {
                        when (workInfo.state) {
                            WorkInfo.State.ENQUEUED -> {
                                //statusTextView.text = "Download is enqueued..."

                            }

                            WorkInfo.State.RUNNING -> {
                                val progress = workInfo.progress.getInt("progress", 0)
                            }

                            WorkInfo.State.SUCCEEDED -> {

                                // Access the output data from the worker
                                val productDataJson = workInfo.outputData.getString("productData")
                                productDataJson?.let {
                                    val productListType =
                                        object : TypeToken<List<Product>>() {}.type
                                    val products: List<Product> =
                                        Gson().fromJson(it, productListType)


                                    displayProductData(products)
                                }
                            }

                            WorkInfo.State.FAILED -> {
                            }

                            WorkInfo.State.CANCELLED -> {

                            }

                            else -> {

                            }
                        }
                    }
                })
        }

        //====================


        comm = activity as FragmentB2.Communicator

        myRV = view.findViewById(R.id.RV1)
        //myRVAdapter( = RVAdapter())
        myRVAdapter = RVAdapter(this, this)
        myRV.adapter = myRVAdapter
        val myLayoutManager  = LinearLayoutManager(this.getContext())
        myRV.layoutManager = myLayoutManager

        //myRVAdapter.submitList(productList)



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

