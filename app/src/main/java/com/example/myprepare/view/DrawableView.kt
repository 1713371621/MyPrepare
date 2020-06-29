package com.example.myprepare.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.myprepare.dp
import com.example.myprepare.drawable.MeshDrawable

class DrawableView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
  
  val drawable: MeshDrawable = MeshDrawable()
  
  private val animator: ObjectAnimator = ObjectAnimator.ofFloat(this, "interval", 50.dp, 100.dp)
  
  private var interval: Float = 50.dp
    set(value) {
      field = value
      invalidate()
    }
  
  init {
    animator.duration = 5000
    animator.interpolator = LinearInterpolator()
    animator.repeatCount = ValueAnimator.INFINITE
    animator.repeatMode = ValueAnimator.REVERSE
  }
  
  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    animator.start()
  }
  
  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    animator.end()
  }
  
  override fun onDraw(canvas: Canvas) {
    drawable.interval = interval
    drawable.setBounds(0, 0, width, height)
    drawable.draw(canvas)
  }
}