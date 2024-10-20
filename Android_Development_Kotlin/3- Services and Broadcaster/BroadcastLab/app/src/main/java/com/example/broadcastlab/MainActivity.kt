package com.example.broadcastlab

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnDownload : Button
    lateinit var editTextUrl : EditText
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnDownload = findViewById(R.id.btnDownload)
        editTextUrl = findViewById(R.id.editTextUrl)
        editTextUrl.setText("https://cdn.dummyjson.com/products/images/groceries/Kiwi/thumbnail.png")
        btnDownload.setOnClickListener{
            var intent = Intent(this,DownloadIntentService::class.java)
            intent.putExtra("url",editTextUrl.text.toString())
            startService(intent)
        }
    }
}