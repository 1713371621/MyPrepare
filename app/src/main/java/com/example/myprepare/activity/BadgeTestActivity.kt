package com.example.myprepare.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.drawBadge

class BadgeTestActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_badge_test)
    
    drawBadge()
  }
}