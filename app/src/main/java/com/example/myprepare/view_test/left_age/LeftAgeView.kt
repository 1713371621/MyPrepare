package com.example.myprepare.view_test.left_age

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.example.myprepare.R

class LeftAgeView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

  private var lottieAnimationView: LottieAnimationView
  private var leftAgeText: ScrollerNumberView

  companion object {
    private const val TAG = "LeftAgeView"
  }

  init {
    LayoutInflater.from(context).inflate(R.layout.view_left_age, this)
    lottieAnimationView = findViewById(R.id.left_age_animator)
    lottieAnimationView.setAnimation("animation/left_age/dark.zip")
    leftAgeText = findViewById(R.id.left_age_text)

  }

  fun startRefreshLeftAge(number: Int) {
    leftAgeText.number = number
    lottieAnimationView.playAnimation()
    leftAgeText.startHindAnimator(480, 340)
    leftAgeText.startShowAnimator(1f, 800, 820)
    leftAgeText.startScrollerAnimator(0.5f, 800, 820)
  }
}