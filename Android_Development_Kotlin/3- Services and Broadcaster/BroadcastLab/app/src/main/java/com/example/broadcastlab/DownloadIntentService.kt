package com.example.broadcastlab

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.content.ContextCompat
import java.io.DataOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class DownloadIntentService : IntentService("DownloadIntentService") {
    val FILE_NAME: String = "FILE_NAME"
    override fun onHandleIntent(intent: Intent?) {
        val intentFilter: IntentFilter = IntentFilter("BroadCastSent")
        var receiver = MyReceiver()
        ContextCompat.registerReceiver(this, MyReceiver(),intentFilter,ContextCompat.RECEIVER_EXPORTED)
        ContextCompat.RECEIVER_EXPORTED
        var url = intent?.getStringExtra("url")
        if (url != null) {
            val bitmap = download(url)
            store(bitmap)
            broadcastIntent()
        }
    }
    @Throws(IOException::class)
    private fun download(url: String): Bitmap {
        val imgURL = URL(url)
        val connection = imgURL.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()
        val responsecode = connection.responseCode
        val `is` = connection.inputStream
        val bitmap = BitmapFactory.decodeStream(`is`)
        return bitmap
    }
    private fun store(bitmap: Bitmap) {
        val fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
        try {
            // Compress and save the bitmap to the file output stream
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } finally {
            fos.close()
        }
    }
    fun broadcastIntent(){
        val intent = Intent()
        intent.action = "BroadCastSent"
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        sendBroadcast(intent)
    }
}