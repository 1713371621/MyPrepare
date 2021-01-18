package com.example.myprepare.view_test.left_age

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myprepare.R
import kotlinx.android.synthetic.main.activity_left_age_animation.*

class LeftAgeAnimationActivity : AppCompatActivity() {
  var number: Int = 8
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_left_age_animation)

    addNumber.setOnClickListener {
      number ++
      scrollerNumberView.number = number
      scrollerNumberView.startScrollerAnimator(1f, 400, 0)
    }

    leftAgeView.setOnClickListener {
      leftAgeView.startRefreshLeftAge(++ number)
    }
  }
}