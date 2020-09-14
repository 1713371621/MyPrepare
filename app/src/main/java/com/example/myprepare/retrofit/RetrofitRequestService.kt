package com.example.myprepare.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitRequestService {

    @GET("users/1713371621/repos")
    fun getRepos(): Call<List<Repo>>
}