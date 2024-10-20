package com.example.myworkmanager

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class ProductResponse(
    val products: List<Product>
)
@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val brand: String,
    val thumbnail: String
): Serializable
