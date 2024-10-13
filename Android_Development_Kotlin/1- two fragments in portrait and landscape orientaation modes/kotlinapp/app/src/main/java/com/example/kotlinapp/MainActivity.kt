package com.example.kotlinapp

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager

lateinit var btn : Button
    lateinit var text : TextView
    lateinit var plaintext : EditText
    private lateinit var fmgr: FragmentManager

val productList = mutableListOf(
Product(
id = 1,
title = " Mascara Lash Princess",
description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects.",
category = "beauty",
price = 9.99,
discountPercentage = 7.17,
rating = 4.94,
stock = 5,
tags = listOf("beauty", "mascara"),
brand = "Essence",
sku = "RCH45Q1A",
weight = 2,
warrantyInformation = "1 month warranty",
shippingInformation = "Ships in 1 month",
availabilityStatus = "Low Stock",
returnPolicy = "30 days return policy",
minimumOrderQuantity = 24,
images = listOf("https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/1.png"),
thumbnail = "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png"
)
)

class MainActivity : AppCompatActivity() , FragmentB2.Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentA, FragmentA())
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
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentB2, FragmentB2())
                    .commit()
            }

        }
        else{

            if (resources.configuration.orientation != Configuration.ORIENTATION_PORTRAIT) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentB2, FragmentB2())
                    .commit()
            }
        }




    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
       Log.i("TAG", "451215")
       // Save the counter value into the Bundle
       //val myFragment = fmgr.findFragmentById(R.id.fragmentB2)
       //if (myFragment is FragmentB2) {
       //    // Assuming the counter value is stored in DynamicFragment as a String
       //    outState.putSerializable("PRODUCT_LIST", ArrayList(productList))
       //}
    }

    override fun respond(productList: List<Product>) {

        fmgr = supportFragmentManager
        val myFragment = fmgr.findFragmentById(R.id.fragmentB2)

        if (myFragment is FragmentB2) {
            myFragment.changeData(productList)
            myFragment.test(productList)
        }

    }

}

