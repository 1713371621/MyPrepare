package com.example.myprepare.view_test.left_age

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R

class LeftAgeAnimationActivity : AppCompatActivity() {
  var number: Int = 995
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_left_age_animation)

    val addNumber: Button = findViewById(R.id.addNumber)
    val scrollerNumberView: ScrollerNumberView = findViewById(R.id.scrollerNumberView)

    addNumber.setOnClickListener {
      number++
      scrollerNumberView.number = number
      scrollerNumberView.startScrollerAnimator(1f, 400, 0)
    }

    val leftAgeView: LeftAgeView = findViewById(R.id.leftAgeView)

    leftAgeView.setOnClickListener {
      leftAgeView.startRefreshLeftAge(++number)
    }
  }
}