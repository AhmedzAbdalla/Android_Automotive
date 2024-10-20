package com.example.kotlinapp

import Product
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myworkmanager.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentB2 : Fragment() {

    private lateinit var productList: MutableList<Product>

    private lateinit var myProduct: Product
    var myRVAdapter : RVDetailsAdapter = RVDetailsAdapter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Restore productList if savedInstanceState is not null
        if (savedInstanceState != null) {
            Log.i("TAG", "savedInstanceState is true ")
            productList = savedInstanceState.getSerializable("PRODUCT_LIST") as MutableList<Product>
            Log.i("TAG", "productList")
            Log.i("TAG", productList.get(0).description)

        } else {
            Log.i("TAG", "onCreate: ")
            // Initialize with default values if there is no saved state
            productList = mutableListOf(
                Product(
                    id = 1,
                    title = "Essence Mascara Lash Princess",
                    description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects.",
                    price = 9.99,
                    brand = "Essence",
                   thumbnail = "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png"
                )
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i("TAG", "$$$$$$$$$$$$$$$$$$$$$$$")
        return inflater.inflate(R.layout.fragment_b2, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            // Initialize RecyclerView
            val myRV = view.findViewById<RecyclerView>(R.id.RV3)
            myRV.adapter = myRVAdapter
            val myLayoutManager = LinearLayoutManager(context)
            myRV.layoutManager = myLayoutManager
            myRVAdapter.submitList(productList)
           // myRVAdapter.submitList(productList)
        //Log.i("TAG", productList[0].category)

        Log.i("TAG", "@@@@$#$#$#@@@@")

            //myRVAdapter.submitList(productList)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the productList to the outState bundle

        Log.i("TAG", "onSaveInstanceState: ")
        Log.i("TAG", productList.get(0).description)

            outState.putSerializable("PRODUCT_LIST", ArrayList(productList))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG", "onDestroy: ")
    }

    fun test(myproductList: List<Product>)
    {
        myRVAdapter.submitList(myproductList)
    }


    fun changeData(myproductList: List<Product>) {
        if (myproductList.isNotEmpty()) {
            // Clear the existing productList
           productList.clear()
            Log.i("TAG", "changeData: ")

            //productList[0] = myproductList[0].copy(title = myproductList[0].title) // Change the title


            // Add all new products to productList
            productList.addAll(myproductList)
            Log.i("TAG", "changeData: ${productList.get(0).description}")

            // Update the adapter with the new list
            myRVAdapter.submitList(myproductList)
            //fragmentManager?.beginTransaction()?.remove(this)?.commit()

            Log.i("TAG", "Product list updated successfully.")
            //Log.i("TAG", "New first product title: ${productList[0].title}")
        } else {
            Log.i("TAG", "Product list is empty. Cannot update.")
        }
    }


    interface Communicator {
        fun respond(productList: List<Product>)

    }
}