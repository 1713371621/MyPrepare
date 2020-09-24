package com.example.myprepare.retrofit

import okhttp3.Request
import retrofit2.Call
import retrofit2.http.*
import rx.Single

interface RetrofitRequestService {

    @GET("users/{id}/repos")
    fun getRepos(@Path("id") id: String): Call<List<Repo>>

    @GET("users/{user}/repos")
    fun getReposRx(@Path("user") user: String): Single<List<Repo>>

    @Headers("sign_method: HMAC-SHA256")
    @GET("v1.0/token")
    fun getAccessToken(
        @Header("client_id") clientId: String?,
        @Header("t") tim: String?,
        @Header("sign") sign: String?,
        @Query("grant_type") grantType: Int
    ): Call<Request?>?
}