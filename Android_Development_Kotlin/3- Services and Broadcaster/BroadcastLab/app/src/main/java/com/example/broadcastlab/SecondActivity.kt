package com.example.broadcastlab

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        imageView = findViewById(R.id.imageView)

        val fis = openFileInput("FILE_NAME")
        val bitmap = BitmapFactory.decodeStream(fis)
        imageView.setImageBitmap(bitmap)
    }
}