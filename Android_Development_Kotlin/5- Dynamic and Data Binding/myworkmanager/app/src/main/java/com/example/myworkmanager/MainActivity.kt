package com.example.myworkmanager

import com.example.myworkmanager.Product

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.kotlinapp.FragmentA
import com.example.kotlinapp.FragmentB2
import com.example.myworkmanager.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.TimeUnit

private lateinit var fmgr: FragmentManager

lateinit var binding: ActivityMainBinding

//import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity(), FragmentB2.Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //2. Initialize your variable by calling BindingClass.inflate
        binding = ActivityMainBinding.inflate(layoutInflater)
        //3. Use setContentView with binding
        setContentView(binding.root)
        enableEdgeToEdge()
        //binding.fragmentA.id

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentA.id, FragmentA())
                .commit()

            // Check the orientation and load the appropriate fragment
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Load FragmentA in portrait mode
               // supportFragmentManager.beginTransaction()
               //     .replace(R.id.fragmentA, FragmentA())
               //     .commit()
            } else {
                // Load FragmentA in portrait mode
               // supportFragmentManager.beginTransaction()
               //     .replace(R.id.fragmentA, FragmentA())
               //     .commit()
                // Load FragmentB2 in landscape mode
                binding.fragmentB2?.id?.let {
                    supportFragmentManager.beginTransaction()
                        .replace(it, FragmentB2())
                        .commit()
                }
            }

        }
        else{
            if (resources.configuration.orientation != Configuration.ORIENTATION_PORTRAIT) {
                binding.fragmentB2?.let {
                    supportFragmentManager.beginTransaction()
                        .replace(it.id, FragmentB2())
                        .commit()
                }
            }
        }

        // Create the WorkRequest for ImageDownloadWorker
        val downloadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInputData(workDataOf("code1" to "hi", "code2" to "Hello"))
            //retry policy
            .setBackoffCriteria(BackoffPolicy.LINEAR,1L,TimeUnit.MINUTES)
            .build()


        // Enqueue the WorkRequest
        WorkManager.getInstance(this).enqueue(downloadWorkRequest)

        // Observe the work status and progress
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(downloadWorkRequest.id)
            .observe(this, Observer { workInfo ->
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
                                val productListType = object : TypeToken<List<Product>>() {}.type
                                val products: List<Product> = Gson().fromJson(it, productListType)


                                //displayProductData(products)
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

    private fun displayProductData(products: List<Product>) {
        // Loop through the products and access individual attributes
        products.forEach { product ->
            // Access each attribute individually
            val id = product.id
            val title = product.title
            val description = product.description
            val price = product.price
            val thumbnail = product.thumbnail

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

    override fun respond(productList: List<Product>) {
        fmgr = supportFragmentManager
        val myFragment = binding.fragmentB2?.let { fmgr.findFragmentById(it.id) }

        if (myFragment is FragmentB2) {
            myFragment.changeData(productList)
            myFragment.test(productList)
        }
    }

}

