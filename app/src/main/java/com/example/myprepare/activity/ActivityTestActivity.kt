package com.example.myprepare.activity

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.generic_test.Apple
import kotlinx.android.synthetic.main.activity_test.*
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class ActivityTestActivity : AppCompatActivity() {
  
  companion object {
    
    private const val TAG = "ActivityTestActivity"
  }
  
  var message: String = "?写个死锁"
  
  override fun onCreate(savedInstanceState: Bundle?) {
    Log.d(TAG, "onCreate: top: message = $message")
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_test)
    Log.d(
      TAG,
      "onCreate: bottom: savedInstanceState.message = ${savedInstanceState?.getString("message")}"
    )
    
    addTouchListener()
  }
  
  fun addTouchListener() {
    val weakReference: WeakReference<Apple> = WeakReference<Apple>(Apple("fuck"));
    System.gc()
  
    activity_test_button.setOnClickListener {
      if (weakReference.get() == null) {
        Log.d(TAG, "addTouchListener: is empty")
      } else {
        Log.d(TAG, "addTouchListener: ${weakReference.get()?.name}")
      }
      
    }
    weakReference.get()?.name = "apple"
  }
  
  override fun onStart() {
    Log.d(TAG, "onStart: ")
    super.onStart()
  }
  
  override fun onResume() {
    Log.d(TAG, "onResume: ")
    super.onResume()
  }
  
  override fun onPause() {
    Log.d(TAG, "onPause: ")
    super.onPause()
  }
  
  override fun onStop() {
    Log.d(TAG, "onStop: ")
    super.onStop()
  }
  
  override fun onRestart() {
    Log.d(TAG, "onRestart: ")
    super.onRestart()
  }
  
  override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    Log.d(TAG, "onRestoreInstanceState: ${savedInstanceState.getString("message")}")
    message = savedInstanceState.getString("message") ?: message
    super.onRestoreInstanceState(savedInstanceState)
  }
  
  override fun onSaveInstanceState(outState: Bundle) {
    Log.d(TAG, "onSaveInstanceState: ")
    outState.putString("message", "老八秘制小汉堡")
    super.onSaveInstanceState(outState)
  }
}