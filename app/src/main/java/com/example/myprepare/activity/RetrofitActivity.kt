package com.example.myprepare.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myprepare.R
import com.example.myprepare.retrofit.Repo
import com.example.myprepare.retrofit.RetrofitRequestService
import com.example.myprepare.retrofit.RetrofitHelper
import com.google.gson.Gson
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

        val gson = Gson()
        // enqueue: 异步 execute: 同步
        retrofitRequestService.getRepos().enqueue(object : Callback<List<Repo>> {

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Logger.d(gson.toJson(response.body()))
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                t.printStackTrace()
                Logger.e("")
            }

        })
    }
}