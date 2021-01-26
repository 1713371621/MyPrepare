package com.example.myprepare.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R

class TouchEventTestActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_touch_event_test)
  }

  override fun onUserInteraction() {
    super.onUserInteraction()
  }
}