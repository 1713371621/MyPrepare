package com.example.myprepare.jet_pack

import com.example.myprepare.jet_pack.bean.User
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Path

interface Webservice {

  @GET("/users/{user}")
  suspend fun getUser(@Path("user") userId: String): User
}