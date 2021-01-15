package com.example.myprepare.view_test

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import kotlinx.android.synthetic.main.activity_view.*

class ViewActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_view)
    
    val dashBoardAnimation: ValueAnimator = ObjectAnimator.ofInt(dash_board_view, "index", 0, 101)
    dashBoardAnimation.interpolator = LinearInterpolator()
    dashBoardAnimation.duration = 5000
    dashBoardAnimation.repeatCount = ValueAnimator.INFINITE
    dashBoardAnimation.repeatMode = ValueAnimator.REVERSE
    dashBoardAnimation.start()
  }
}