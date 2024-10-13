package com.example.kotlinapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentA : Fragment() ,OnItemClickListener{


    lateinit var myRV : RecyclerView
    lateinit var myRVAdapter : RVAdapter
    private lateinit var comm: FragmentB2.Communicator

    val productList = listOf(
        Product(
            id = 1,
            title = "Essence Mascara Lash Princess",
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
        ),
        Product(
            id = 2,
            title = "Maybelline Fit Me Foundation",
            description = "A lightweight foundation that provides a natural finish.",
            category = "beauty",
            price = 12.99,
            discountPercentage = 10.00,
            rating = 4.5,
            stock = 20,
            tags = listOf("foundation", "makeup"),
            brand = "Maybelline",
            sku = "MAYBELLE123",
            weight = 1,
            warrantyInformation = "No warranty",
            shippingInformation = "Ships in 2 days",
            availabilityStatus = "In Stock",
            returnPolicy = "14 days return policy",
            minimumOrderQuantity = 1,
            images = listOf("https://example.com/images/foundation1.png"),
            thumbnail = "https://example.com/images/foundation_thumbnail.png"
        ),
        Product(
            id = 3,
            title = "L'Oreal Paris Hair Color",
            description = "Long-lasting hair color with a rich and vibrant finish.",
            category = "beauty",
            price = 8.49,
            discountPercentage = 5.00,
            rating = 4.8,
            stock = 15,
            tags = listOf("hair color", "beauty"),
            brand = "L'Oreal",
            sku = "LOREAL123",
            weight = 3,
            warrantyInformation = "No warranty",
            shippingInformation = "Ships in 3 days",
            availabilityStatus = "In Stock",
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 2,
            images = listOf("https://example.com/images/hair_color1.png"),
            thumbnail = "https://example.com/images/hair_color_thumbnail.png"
        ),
        Product(
            id = 4,
            title = "Revlon Super Lustrous Lipstick",
            description = "A highly pigmented lipstick that glides on smoothly.",
            category = "beauty",
            price = 7.99,
            discountPercentage = 12.00,
            rating = 4.7,
            stock = 30,
            tags = listOf("lipstick", "makeup"),
            brand = "Revlon",
            sku = "REVLON123",
            weight = 1,
            warrantyInformation = "No warranty",
            shippingInformation = "Ships in 1 week",
            availabilityStatus = "In Stock",
            returnPolicy = "14 days return policy",
            minimumOrderQuantity = 1,
            images = listOf("https://example.com/images/lipstick1.png"),
            thumbnail = "https://example.com/images/lipstick_thumbnail.png"
        ),
        Product(
            id = 5,
            title = "NYX Professional Makeup Eyeshadow Palette",
            description = "A versatile eyeshadow palette with a mix of matte and shimmer shades.",
            category = "beauty",
            price = 15.00,
            discountPercentage = 8.00,
            rating = 4.6,
            stock = 25,
            tags = listOf("eyeshadow", "makeup"),
            brand = "NYX",
            sku = "NYX123",
            weight = 2,
            warrantyInformation = "No warranty",
            shippingInformation = "Ships in 5 days",
            availabilityStatus = "In Stock",
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 1,
            images = listOf("https://example.com/images/eyeshadow_palette.png"),
            thumbnail = "https://example.com/images/eyeshadow_thumbnail.png"
        ),
        Product(
            id = 6,
            title = "Clinique Moisture Surge",
            description = "A refreshing gel-cream that provides instant hydration.",
            category = "beauty",
            price = 38.00,
            discountPercentage = 15.00,
            rating = 4.9,
            stock = 10,
            tags = listOf("skincare", "moisturizer"),
            brand = "Clinique",
            sku = "CLINIQUE123",
            weight = 4,
            warrantyInformation = "No warranty",
            shippingInformation = "Ships in 3 days",
            availabilityStatus = "Low Stock",
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 1,
            images = listOf("https://example.com/images/moisture_surge.png"),
            thumbnail = "https://example.com/images/moisture_surge_thumbnail.png"
        ),
        Product(
            id = 7,
            title = "CeraVe Hydrating Facial Cleanser",
            description = "A gentle cleanser that hydrates while removing dirt and makeup.",
            category = "beauty",
            price = 12.00,
            discountPercentage = 10.00,
            rating = 4.8,
            stock = 8,
            tags = listOf("cleanser", "skincare"),
            brand = "CeraVe",
            sku = "CERAVE123",
            weight = 2,
            warrantyInformation = "No warranty",
            shippingInformation = "Ships in 2 days",
            availabilityStatus = "In Stock",
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 1,
            images = listOf("https://example.com/images/cleanser.png"),
            thumbnail = "https://example.com/images/cleanser_thumbnail.png"
        ),
        Product(
            id = 8,
            title = "Nivea Soft Moisturizing Cream",
            description = "A light, refreshing cream for face, body, and hands.",
            category = "beauty",
            price = 5.99,
            discountPercentage = 20.00,
            rating = 4.5,
            stock = 50,
            tags = listOf("moisturizer", "skincare"),
            brand = "Nivea",
            sku = "NIVEA123",
            weight = 1,
            warrantyInformation = "No warranty",
            shippingInformation = "Ships in 1 week",
            availabilityStatus = "In Stock",
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 1,
            images = listOf("https://example.com/images/nivea_cream.png"),
            thumbnail = "https://example.com/images/nivea_thumbnail.png"
        ),
        Product(
            id = 9,
            title = "Aveeno Daily Moisturizing Lotion",
            description = "A daily lotion that hydrates and protects dry skin.",
            category = "beauty",
            price = 10.50,
            discountPercentage = 5.00,
            rating = 4.6,
            stock = 35,
            tags = listOf("moisturizer", "skincare"),
            brand = "Aveeno",
            sku = "AVEENO123",
            weight = 3,
            warrantyInformation = "No warranty",
            shippingInformation = "Ships in 2 days",
            availabilityStatus = "In Stock",
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 1,
            images = listOf("https://example.com/images/aveeno_lotion.png"),
            thumbnail = "https://example.com/images/aveeno_thumbnail.png"
        ),
        Product(
            id = 10,
            title = "Garnier SkinActive Micellar Cleansing Water",
            description = "A cleansing water that removes makeup and impurities.",
            category = "beauty",
            price = 8.00,
            discountPercentage = 10.00,
            rating = 4.7,
            stock = 18,
            tags = listOf("cleanser", "makeup remover"),
            brand = "Garnier",
            sku = "GARNIER123",
            weight = 2,
            warrantyInformation = "No warranty",
            shippingInformation = "Ships in 3 days",
            availabilityStatus = "Low Stock",
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 1,
            images = listOf("https://example.com/images/micellar_water.png"),
            thumbnail = "https://example.com/images/micellar_thumbnail.png"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //=====================
        outState.putSerializable("test", productList.get(0))
        //=====================

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comm = activity as FragmentB2.Communicator

        myRV = view.findViewById(R.id.RV1)
        //myRVAdapter( = RVAdapter())
        myRVAdapter = RVAdapter(this, this)
        myRV.adapter = myRVAdapter
        val myLayoutManager  = LinearLayoutManager(this.getContext())
        myRV.layoutManager = myLayoutManager

        myRVAdapter.submitList(productList)



    }
    companion object {
      @JvmStatic fun newInstance(param1: String, param2: String) =
                FragmentA().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun onItemClicked(productList: List<Product>) {

        Log.i("tag", productList.get(0).sku)

        comm.respond(productList)
        Log.i("TAG", "I'm here")
    }


}