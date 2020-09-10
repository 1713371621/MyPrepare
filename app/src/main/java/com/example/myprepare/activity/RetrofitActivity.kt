package com.example.myprepare.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myprepare.R
import com.example.myprepare.retrofit.GetRequestInterface
import com.example.myprepare.retrofit.SurrogateResponse
import com.example.myprepare.retrofit.RetrofitHelper
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val getRequest: GetRequestInterface =
            RetrofitHelper.createRetrofit("").create(GetRequestInterface::class.java)

        val call: Call<SurrogateResponse> = getRequest.loginInWithPassword()
        call.enqueue(object : Callback<SurrogateResponse> {
            override fun onFailure(call: Call<SurrogateResponse>, t: Throwable) {
                Logger.d("failure")
            }

            override fun onResponse(call: Call<SurrogateResponse>, surrogateResponse: retrofit2.Response<SurrogateResponse>) {
                Logger.d("response: message = ${surrogateResponse.body()?.message}")
            }

        })
    }
}