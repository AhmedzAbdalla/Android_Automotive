package com.example.kotlinapp

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
import com.example.myworkmanager.Product
import com.example.myworkmanager.R
import com.example.myworkmanager.databinding.ActivityMainBinding
import com.example.myworkmanager.databinding.FragmentABinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.TimeUnit

class FragmentA : Fragment() ,OnItemClickListener{


    lateinit var myRV : RecyclerView
    lateinit var myRVAdapter : RVAdapter
    private lateinit var comm: FragmentB2.Communicator

    lateinit var binding: FragmentABinding

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

        //Step/s 4 for fragment dynamic binding
        myRV = binding.RV1
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

