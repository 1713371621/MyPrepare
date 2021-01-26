package com.example.myprepare.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.retrofit.Repo
import com.example.myprepare.retrofit.RetrofitHelper
import com.example.myprepare.retrofit.RetrofitRequestService
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitActivity : AppCompatActivity() {

  var disposable: Disposable? = null

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
//        retrofitRequestService.getRepos("1713371621").enqueue(object : Callback<List<Repo>> {
//
//            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
//                Logger.d(gson.toJson(response.body()))
//            }
//
//            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
//                t.printStackTrace()
//                Logger.e("")
//            }
//        })

    retrofitRequestService.getReposRx("1713371621")
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(object : SingleObserver<MutableList<Repo>> {
        override fun onSubscribe(d: Disposable?) {
          disposable = d
//          Logger.d("正在请求")
        }

        override fun onSuccess(t: MutableList<Repo>?) {
//          Logger.d(t)
        }

        override fun onError(e: Throwable?) {
//          Logger.e(e, "error")
        }

      })
  }

  override fun onDestroy() {
    disposable?.dispose()
    super.onDestroy()
  }
}