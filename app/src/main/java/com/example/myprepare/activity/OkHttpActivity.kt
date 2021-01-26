package com.example.myprepare.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import okhttp3.*
import java.io.IOException


class OkHttpActivity : AppCompatActivity() {

  val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ok_http)

    val hostname = "hencoder.com"

    val certificatePinner = CertificatePinner.Builder()
      .add(
        hostname,
        "sha256/+OAwmENjrBT/pI2PSOVO/I3OtHeKk7Y0PH9h8Z2z3Nw=",
        "sha256/YLh1dUR9y6Kja30RrAn7JKnbQG/uEtLMkBgFF2Fuihg=",
        "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys="
      )
      .build()

    val client = OkHttpClient.Builder()
      .certificatePinner(certificatePinner)
      .authenticator(object : Authenticator {
        override fun authenticate(route: Route?, response: Response): Request? {
          // request
          return response.request().newBuilder().addHeader("Authorization", "Bearer aaa").build()
        }
      })
      .build()

    val request: Request = Request.Builder()
      .url("https://$hostname")
      .build()

    client.newCall(request).enqueue(object : Callback {
      override fun onFailure(call: Call, e: IOException) {
        e.printStackTrace()
//        Logger.d("")
      }

      override fun onResponse(call: Call, response: Response) {
//        Logger.d(response.body()?.string())
      }

    })


  }
}