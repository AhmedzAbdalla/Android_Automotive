package com.example.myworkmanager

import com.example.myworkmanager.ProductResponse

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response

class MyWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    val data_failure = workDataOf(
        "o/p2_failure" to "test fail output",
    )


    override fun doWork(): Result {

        //this to test the input data as POC
        val x = inputData.getString("code1")
        val y = inputData.getString("code2")
        Log.d("TAG", "input data: $x and $y ")
        // end of the test

        // Get the number of run attempts
        val attempts: Int = runAttemptCount
        Log.d("MyWorker", "Run attempt: $attempts")

        val apiService = RetrofitHelper.getApiService()

        return try {
            // Fetch data using Retrofit synchronously
            val call: Call<ProductResponse> = apiService.fetchProducts()
            val response: Response<ProductResponse> = call.execute() // Synchronous call

            // Check the response
            handleResponse(response)
        } catch (e: HttpException) {
            Log.e("MyWorker", "HTTP Exception: ${e.message}")
            Result.retry() // Retry on network errors
        } catch (e: Exception) {
            Log.e("MyWorker", "Exception: ${e.message}")
            Result.failure(data_failure)
        }
    }

    private fun handleResponse(response: Response<ProductResponse>): Result {
        return if (response.isSuccessful && response.body() != null) {
            val products = response.body()?.products ?: emptyList()

            // Convert the products list to JSON using Gson
            val gson = Gson()
            val productDataJson = gson.toJson(products)

            // Pass the product data back using OutputData
            val outputData = Data.Builder()
                .putString("productData", productDataJson)
                .build()

            // Simulate setting progress to 100% after fetching data
            setProgressAsync(Data.Builder().putInt("progress", 100).build())

            Result.success(outputData)
        } else {
            Log.e("MyWorker", "Response was unsuccessful: ${response.errorBody()?.string()}")
            Result.failure(data_failure)
        }
    }
}
