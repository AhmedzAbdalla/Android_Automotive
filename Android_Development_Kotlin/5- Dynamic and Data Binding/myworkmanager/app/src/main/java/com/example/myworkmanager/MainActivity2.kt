package com.example.myworkmanager

import com.example.myworkmanager.Product

import android.annotation.SuppressLint
import android.content.res.Configuration
import com.example.kotlinapp.FragmentB

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myworkmanager.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //step 1 for activity dynamic binding
        binding = ActivityMain2Binding.inflate(layoutInflater)

        enableEdgeToEdge()
        //step 2 for activity dynamic binding
        setContentView(binding.root)

        val product: Product = intent.getSerializableExtra("PRODUCT_DATA") as Product // For Serializable

        Log.i("TAG", product.title)

        val myBundle = Bundle()
        myBundle.putSerializable("PRODUCT_DATA", product) // Use the same key

        // Pass the bundle to FragmentB when creating the instance
        val fragmentB = FragmentB().apply {
            arguments = myBundle // Set the arguments for the fragment
        }

        supportFragmentManager.beginTransaction()
            //step(s) 3 for activity dynamic binding
            .replace(binding.fragmentB.id, fragmentB)
            .commit()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
        }
    }

}
