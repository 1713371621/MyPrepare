package com.example.myprepare.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.generic_test.Apple
import java.lang.ref.WeakReference

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

    val button: Button = findViewById(R.id.activity_test_button)
    button.setOnClickListener {
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

  override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
    Log.d(TAG, "onSaveInstanceState: ")
    outState.putString("message", "老八秘制小汉堡")
    super.onSaveInstanceState(outState, outPersistentState)
  }
}