package com.example.kotlinapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myworkmanager.Product
import com.example.myworkmanager.R
import com.example.myworkmanager.databinding.FragmentB2Binding
import com.example.myworkmanager.databinding.FragmentBBinding
import java.io.Serializable


class FragmentB : Fragment() {
    private lateinit var myProduct: Product // Declare product variable

    lateinit var binding : FragmentBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the Product object from arguments
        arguments?.let {
            myProduct = it.getSerializable("PRODUCT_DATA") as Product // Retrieve using the same key
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //step 1 for fragmentdynamic binding
        binding = FragmentBBinding.inflate(inflater,container, false )
        //step 2 for fragment dynamic binding
        val view = binding.root
        //step 3 for fragment dynamic binding
        return view
        //return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Check if myProduct is retrieved correctly
        if (::myProduct.isInitialized) {
            Log.i("TAG", "Product Title: ${myProduct.title}")
            val productList = mutableListOf<Product>()
            productList.add(myProduct) // Add to the list

            // Initialize RecyclerView
//            val myRV = view.findViewById<RecyclerView>(R.id.RV2)
            //step(s) for fragment dynamic view
            val myRV = binding.RV2

            val myRVAdapter = RVDetailsAdapter()
            myRV.adapter = myRVAdapter
            val myLayoutManager = LinearLayoutManager(context)
            myRV.layoutManager = myLayoutManager

            myRVAdapter.submitList(productList)
        } else {
            Log.i("TAG", "Product is not initialized")
        }
    }



    companion object {
        @JvmStatic
        fun newInstance(myproduct: Product) =
            FragmentB().apply {
                arguments = Bundle().apply {
                    // You can add parameters here if needed
                }
            }
    }
}
