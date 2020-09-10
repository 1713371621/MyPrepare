package com.example.myprepare.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myprepare.R
import com.example.myprepare.retrofit.RetrofitRequestService
import com.example.myprepare.retrofit.RetrofitHelper
import com.orhanobut.logger.Logger
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "RetrofitActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofitRequestService: RetrofitRequestService =
            RetrofitHelper.createRetrofit("https://api.github.com/")
                .create(RetrofitRequestService::class.java)

        // enqueue异步 execute同步
        retrofitRequestService.getRepos().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d(TAG, "onResponse: responseBody = ${response.body()?.string()}")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }
}