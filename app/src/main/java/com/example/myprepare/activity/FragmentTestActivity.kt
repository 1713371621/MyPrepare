package com.example.myprepare.activity

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myprepare.R
import com.example.myprepare.fragment.TestFragment

class FragmentTestActivity : AppCompatActivity() {

  companion object {

    private const val TAG = "FragmentTestActivity"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fragment_test)
    Log.d(TAG, "onCreate: ")
    val frameLayout: FrameLayout = findViewById(R.id.fragment_frame_layout)
    frameLayout.viewTreeObserver.addOnGlobalLayoutListener {
      Log.d(
        TAG,
        "onGlobalLayout: "
      )
    }

    val fragmentManager = supportFragmentManager
    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

    val fragment: Fragment? = fragmentManager.findFragmentByTag(TestFragment::class.java.simpleName)
    if (fragment == null) {
      Log.d(TAG, "onCreate: fragment is null")
      fragmentTransaction.add(
        R.id.fragment_frame_layout,
        TestFragment(),
        TestFragment::class.java.simpleName
      )
    } else {
      Log.d(TAG, "onCreate: fragment is not null")
    }
    fragmentTransaction.commit()

  }

  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart: ")
  }

  override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume: ")
  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause: ")
  }

  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop: ")
  }

  override fun onRestart() {
    super.onRestart()
    Log.d(TAG, "onRestart: ")
  }

  override fun onDestroy() {
    Log.d(TAG, "onDestroy: ")
    super.onDestroy()
  }
}