package com.example.myprepare.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myprepare.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesTestActivity : AppCompatActivity() {
  
  companion object {
  
    private const val TAG = "CoroutinesTestActivity"
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_coroutines_test)
    test()
  }
  
  fun test() {
    GlobalScope.launch(Dispatchers.IO) {
      withContext(Dispatchers.Main) {
        Log.d(TAG, "test: fuck you! thread name = ${Thread.currentThread().name}")
      }
      Log.d(TAG, "test: thread name = ${Thread.currentThread().name}")
    }
  }
}