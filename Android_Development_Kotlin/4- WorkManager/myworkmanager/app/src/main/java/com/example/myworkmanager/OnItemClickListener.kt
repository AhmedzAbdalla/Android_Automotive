package com.example.kotlinapp

import Product

interface OnItemClickListener {

    fun onItemClicked(productList: List<Product>)
}