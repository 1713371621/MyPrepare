package com.example.myprepare.activity

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TypeEvaluator
import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.dp
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_animation)

    /*animation_image_view.animate()
        .translationX(200.dp)
        .translationY(100.dp)
        .alpha(0.5f)
        .scaleX(2f)
        .scaleY(2f)
        .rotation(30f)
        .startDelay = 1000*/

    /*val animator: ValueAnimator = ObjectAnimator.ofFloat(animation_circle_view, "radius", 60.dp)
    animator.startDelay = 1000
    animator.start()

    val bottomFlipAnimator = ObjectAnimator.ofFloat(animation_camera_view, "bottomFlip", 40f)
    bottomFlipAnimator.startDelay = 1000
    bottomFlipAnimator.duration = 1000
    //    bottomFlipAnimator.start()

    val topFlipAnimation = ObjectAnimator.ofFloat(animation_camera_view, "topFlip", -40f)
    topFlipAnimation.duration = 1000
    //    topFlipAnimation.start()

    val flipRotationAnimation = ObjectAnimator.ofFloat(animation_camera_view, "flipRotation", 120f)
    flipRotationAnimation.startDelay = 1000
    flipRotationAnimation.duration = 1000
    //    flipRotationAnimation.start()

    val animatorSet = AnimatorSet()
    animatorSet.playSequentially(topFlipAnimation, bottomFlipAnimator, flipRotationAnimation)
    animatorSet.startDelay = 1000
    animatorSet.start()*/

    // cameraView的动画
    val topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", -40f)
    val bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 40f)
    val flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", -120f)

    val holderAnimator = ObjectAnimator.ofPropertyValuesHolder(animation_camera_view, topFlipHolder, bottomFlipHolder, flipRotationHolder)
    holderAnimator.startDelay = 1000
    holderAnimator.duration = 1000
    holderAnimator.start()

    // 图片的动画
    val length = (-200).dp

    val keyframe1: Keyframe = Keyframe.ofFloat(0f, 0f)
    val keyframe2: Keyframe = Keyframe.ofFloat(0.4f, 0.4f * length)
    val keyframe3: Keyframe = Keyframe.ofFloat(0.8f, 0.6f * length)
    val keyframe4: Keyframe = Keyframe.ofFloat(1f, length)

    val keyframesHolder = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3, keyframe4)
    val imageAnimator = ObjectAnimator.ofPropertyValuesHolder(animation_image_view, keyframesHolder)
    imageAnimator.startDelay = 1000
    imageAnimator.duration = 1000
    imageAnimator.start()

    // 点的动画
    val pointFAnimator = ObjectAnimator.ofObject(
        animation_point_f,
        "point",
        PointFEvaluator(),
        PointF(100.dp, 300.dp)
    )
    pointFAnimator.startDelay = 1000
    pointFAnimator.duration = 1000
    pointFAnimator.start()
  }

  class PointFEvaluator : TypeEvaluator<PointF> {

    override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
      val startX: Float = startValue.x
      val startY: Float = startValue.y

      val endX: Float = endValue.x
      val endY: Float = endValue.y

      val currentX = startX + fraction * (endX - startX)
      val currentY = startY + fraction * (endY - startY)

      return PointF(currentX, currentY)
    }

  }
}