package com.example.kotlinapp

import com.example.myworkmanager.Product

interface OnItemClickListener {

    fun onItemClicked(productList: List<Product>)
}