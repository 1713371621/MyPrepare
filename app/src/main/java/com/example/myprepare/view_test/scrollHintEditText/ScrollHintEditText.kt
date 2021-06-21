package com.example.myprepare.view_test.scrollHintEditText

import android.animation.*
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import com.example.myprepare.dp
import kotlin.math.abs
import kotlin.math.roundToInt

class ScrollHintEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

  private val hintList: MutableList<String> = ArrayList()
  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val fontMetrics: Paint.FontMetrics
  private var position: Int = 0
  var progress: Float = 0f
    set(value) {
      field = value
      invalidate()
    }

  companion object {
    private const val PAINT_ALPHA = 255
    private const val TAG = "ScrollHintEditText"
  }

  val animatorRunnable: Runnable = Runnable {
    if (scrollerAnimator.isRunning) {
      scrollerAnimator.cancel()
    }
    if (position >= hintList.size) {
      position = 0
    } else {
      position++
    }

    setHintText(position)

    Log.d(TAG, "Runnable: firstHintTextCount: $firstHintTextCount")
    Log.d(TAG, "Runnable: secondHintTextCount: $secondHintTextCount")
    startScrollerAnimator()
  }

  private fun setHintText(position: Int) {
    firstHintText = getHintText(position)
    firstHintTextCount = getHintTextCount(firstHintText)

    secondHintText = getHintText(position + 1)
    secondHintTextCount = getHintTextCount(secondHintText)
  }

  private val scrollerAnimator: ValueAnimator by lazy {
    val keyframe1: Keyframe = Keyframe.ofFloat(0f, 0f)
    val keyframe2: Keyframe = Keyframe.ofFloat(0.25f, 0.42f * scrollY)
    val keyframe3: Keyframe = Keyframe.ofFloat(0.5f, 0.50f * scrollY)
    val keyframe4: Keyframe = Keyframe.ofFloat(0.75f, 0.58f * scrollY)
    val keyframe5: Keyframe = Keyframe.ofFloat(1f, 1f)

    val screenKeyframesHolder = PropertyValuesHolder.ofKeyframe(
      "progress",
      keyframe1,
      keyframe2,
      keyframe3,
      keyframe4,
      keyframe5
    )

    ObjectAnimator.ofPropertyValuesHolder(this, screenKeyframesHolder).apply {
      addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {
          Log.d(TAG, "onAnimationStart: ")
        }

        override fun onAnimationEnd(animation: Animator?) {
          Log.d(TAG, "onAnimationEnd: ")
          removeCallbacks(animatorRunnable)
          postDelayed(animatorRunnable, 2500)
        }

        override fun onAnimationCancel(animation: Animator?) {
          Log.d(TAG, "onAnimationCancel: ")
        }

        override fun onAnimationRepeat(animation: Animator?) {
          Log.d(TAG, "onAnimationRepeat: ")
        }

      })
      duration = 600
    }
  }

  private val testPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val textSpace: Float = 14f.dp

  private var firstHintText: String = ""
  private var firstHintTextCount: Int = 0
  private var secondHintText: String = ""
  private var secondHintTextCount: Int = 0

  private var textHeight: Float

  init {
    testPaint.color = Color.GREEN
    testPaint.style = Paint.Style.STROKE
    testPaint.strokeWidth = 2f.dp

    hintList.add("哈哈哈哈哈哈")
    hintList.add("嘻嘻嘻嘻嘻嘻")
    hintList.add("叽叽喳喳叽叽喳喳")
    hintList.add("噼噼啪啪噼噼啪啪")
    hintList.add("嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎")
    hintList.add("一二三四五六柒捌玖123456789")

    paint.color = currentHintTextColor
    paint.textSize = textSize
    fontMetrics = Paint.FontMetrics()
    paint.getFontMetrics(fontMetrics)

    setHintText(0)

    textHeight = abs(fontMetrics.top + fontMetrics.bottom)
  }


  private fun getHintTextCount(text: String): Int {
    return paint.breakText(
      text,
      0,
      text.length,
      true,
      maxWidth.toFloat(),
      floatArrayOf(0f)
    )
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    if (!startHintScrollAnimator || firstHintTextCount == 0 || secondHintTextCount == 0 || firstHintText.isBlank() || secondHintText.isBlank()) {
      return
    }
    paint.alpha = (PAINT_ALPHA * (1 - progress)).toInt()
    canvas.drawText(firstHintText, 0, firstHintTextCount, 0f, (height + textHeight) / 2 - (textHeight + textSpace) * progress, paint)
    paint.alpha = (PAINT_ALPHA * progress).toInt()
    canvas.drawText(secondHintText, 0, secondHintTextCount, 0f, (height + textHeight) / 2f + (textHeight + textSpace) * (1 - progress), paint)
  }

  private var startHintScrollAnimator: Boolean = false

  private fun clearHintText() {
    removeCallbacks(animatorRunnable)
    scrollerAnimator.cancel()
    position = 0
    progress = 0f
    setHintText(0)
    if (startHintScrollAnimator) {
      startHintScrollAnimator = false
      invalidate()
    }
  }

  private fun startAnimator() {
    Log.d(TAG, "startAnimator: ")
    startHintScrollAnimator = true
    startScrollerAnimator(2500)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    startAnimator()
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    clearHintText()
  }

  private fun stopHintScrollAnimator() {
    removeCallbacks(animatorRunnable)
    scrollerAnimator.cancel()
    position += progress.roundToInt()
    setHintText(position)
    progress = 0f
    invalidate()
  }

  private fun getHintText(index: Int): String {
    if (hintList.isNotEmpty()) {
      val i = index % hintList.size
      return hintList[i]
    }
    return ""
  }

  private fun startScrollerAnimator(startDelay: Long = 0) {
    if (!scrollerAnimator.isRunning) {
      scrollerAnimator.startDelay = startDelay
      scrollerAnimator.start()
    }
  }
}