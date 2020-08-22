package com.example.myprepare.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface GetRequestInterface {

    @GET(value = "v1/surrogate/users/login")
    fun loginInWithPassword(): Call<Response>
}