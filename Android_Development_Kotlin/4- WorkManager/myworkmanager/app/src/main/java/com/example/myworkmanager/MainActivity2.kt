package com.example.myworkmanager

import Product
import android.content.res.Configuration
import com.example.kotlinapp.FragmentB

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val product: Product = intent.getSerializableExtra("PRODUCT_DATA") as Product // For Serializable

        Log.i("TAG", product.title)

        val myBundle = Bundle()
        myBundle.putSerializable("PRODUCT_DATA", product) // Use the same key

        // Pass the bundle to FragmentB when creating the instance
        val fragmentB = FragmentB().apply {
            arguments = myBundle // Set the arguments for the fragment
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentB, fragmentB)
            .commit()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
        }
    }

}
