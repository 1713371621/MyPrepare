package com.example.myprepare.view_test

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.dp
import com.example.myprepare.view.ProvinceView
import kotlinx.android.synthetic.main.activity_animation2.*
import kotlin.math.abs

class AnimationActivity2 : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_animation2)

    val provinceAnimator: ObjectAnimator = ObjectAnimator.ofObject(
        animation2_province_view,
        "province",
        ProvinceEvaluator(),
        "台湾省"
    )
    provinceAnimator.startDelay = 1000
    provinceAnimator.duration = 5000
    provinceAnimator.start()

    animation2_avatar_view.animate()
        .translationX(200.dp)
        .setStartDelay(1000)
        .withLayer()
  }

  class ProvinceEvaluator : TypeEvaluator<String> {

    override fun evaluate(fraction: Float, startValue: String, endValue: String): String {
      val startIndex: Int = ProvinceView.provinces.indexOf(startValue)
      val endIndex: Int = ProvinceView.provinces.indexOf(endValue)

      val currentIndex: Int = startIndex + ((abs(endIndex - startIndex) % ProvinceView.provinces.size) * fraction).toInt()

      return ProvinceView.provinces[currentIndex % ProvinceView.provinces.size]
    }

  }
}