package com.example.myprepare.view_test.vote_button

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.view.animation.PathInterpolatorCompat
import com.example.myprepare.dp
import kotlin.math.roundToInt

class VoteButtonView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

  companion object {
    private const val TAG = "VoteButtonView"
  }

  private val titleTextSize: Float by lazy { 14.dp }
  private val countTextSize: Float by lazy { 12.dp }
  private val backgroundRadius: Float by lazy { 15.dp }
  private val titleTextPendingLeft: Float by lazy { 12.dp }
  private val countTextPendingRight: Float by lazy { 10.dp }

  // 未选中时的背景颜色|count颜色
  private var unselectedBackgroundColor = Color.parseColor("#33FFFFFF")

  // 选中时的count颜色
  private var selectedCountBackgroundColor = Color.parseColor("#99149EFF")

  // 选中时的背景颜色
  private var selectedBackgroundColor = Color.parseColor("#66000000")

  private var textColor = Color.parseColor("#FFFFFF")

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val fontMetrics: Paint.FontMetrics = Paint.FontMetrics()

  init {
    paint.getFontMetrics(fontMetrics)
  }

  private var title: String = ""

  private var isUserSelected: Boolean = false
  private var isShowSelectedCount: Boolean = false

  private var selectedNumber: Int = 0
  private var totalNumber: Int = 0

  var showCountProgress: Float = 0f
    set(value) {
      field = value
      invalidate()
    }
  private val showCountAnimator: ValueAnimator by lazy {
    ObjectAnimator.ofFloat(this, "showCountProgress", 0f, 1f).apply {
      duration = 500
      interpolator = PathInterpolatorCompat.create(0.25f, 0.46f, 0.45f, 0.94f)
    }
  }

  private val argbEvaluator = ArgbEvaluator()
  var showCountAlphaProgress: Float = 0f
    set(value) {
      field = value
      invalidate()
    }
  private val showCountAlphaAnimator: ValueAnimator by lazy {
    ObjectAnimator.ofFloat(this, "showCountAlphaProgress", 0f, 1f).apply {
      duration = 100
    }
  }

  var countScrollerProgress: Float = 0f
    set(value) {
      field = value
      invalidate()
    }
  private val countScrollerAnimator: ValueAnimator by lazy {
    ObjectAnimator.ofFloat(this, "countScrollerProgress", 0f, 1f).apply {
      duration = 200
      startDelay = 500
      interpolator = PathInterpolatorCompat.create(0.645f, 0.045f, 0.355f, 1f)
    }
  }

  private val backgroundPath: Path = Path()
  private val countPath: Path = Path()

  private val textBounds: Rect = Rect()

  fun showUnselectedTitle(title: String) {
    clearState()
    this.isShowSelectedCount = false
    this.title = title
    invalidate()
  }

  private fun clearState() {
    isUserSelected = false
    isShowSelectedCount = false
    ratio = 0f
    stopAnimation()
    showCountProgress = 0f
    showCountAlphaProgress = 0f
    countScrollerProgress = 0f
  }

  private fun stopAnimation() {
    if (showCountAnimator.isRunning) {
      showCountAnimator.cancel()
    }
    if (showCountAlphaAnimator.isRunning) {
      showCountAlphaAnimator.cancel()
    }
    if (countScrollerAnimator.isRunning) {
      countScrollerAnimator.cancel()
    }
  }

  // 这个是选中的占全部的比例
  private var ratio: Float = 0f

  fun showSelectedCount(title: String, selectedNumber: Int, totalNumber: Int, isUserSelected: Boolean, showAnimation: Boolean = true) {
    clearState()
    this.title = title
    this.isUserSelected = isUserSelected
    this.selectedNumber = selectedNumber
    this.totalNumber = totalNumber
    this.isShowSelectedCount = true
    ratio = selectedNumber.toFloat() / totalNumber.toFloat()
    if (showAnimation) {
      showCountAnimator.start()
      showCountAlphaAnimator.start()
      countScrollerAnimator.start()
    } else {
      showCountProgress = 1f
      showCountAlphaProgress = 1f
      countScrollerProgress = 1f
    }
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    Log.d(TAG, "onAttachedToWindow: ")
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    Log.d(TAG, "onDetachedFromWindow: ")
    stopAnimation()
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    // 绘制背景
    drawVoteButtonBackground(canvas)

    // 绘制标题
    paint.color = textColor
    paint.textSize = titleTextSize
    paint.getFontMetrics(fontMetrics)
    val titleTextHeight: Float = fontMetrics.top + fontMetrics.bottom
    canvas.drawText(title, titleTextPendingLeft, (height.toFloat() - titleTextHeight) / 2, paint)

    if (!isShowSelectedCount) {
      return
    }

    // 绘制票数
    paint.color = textColor
    paint.textSize = countTextSize
    paint.getFontMetrics(fontMetrics)
    val countTextHeight: Float = fontMetrics.top + fontMetrics.bottom
    val countTextTop: Float = (height.toFloat() - countTextHeight) / 2

    val countText1 = "${(selectedNumber * showCountProgress).roundToInt()}票"
    paint.getTextBounds(countText1, 0, countText1.length, textBounds)

    Log.d(TAG, "onDraw: fontMetrics.bottom: ${fontMetrics.bottom}")
    // 添加一个滚动动画
    canvas.drawText(
      countText1,
      width - textBounds.width() - countTextPendingRight,
      countTextTop * (1 - countScrollerProgress) - fontMetrics.bottom * countScrollerProgress,
      paint
    )

    val countText2 = "${(ratio * 100).roundToInt()}%"
    paint.getTextBounds(countText2, 0, countText2.length, textBounds)
    canvas.drawText(
      countText2,
      width - textBounds.width() - countTextPendingRight,
      height * (1 - countScrollerProgress) + fontMetrics.top * (countScrollerProgress - 1) + countTextTop * countScrollerProgress,
      paint
    )
  }

  private fun drawVoteButtonBackground(canvas: Canvas) {
    backgroundPath.reset()
    // 绘制背景
    paint.color = if (!isShowSelectedCount) {
      unselectedBackgroundColor
    } else {
      argbEvaluator.evaluate(showCountAlphaProgress, unselectedBackgroundColor, selectedBackgroundColor) as Int
    }

    backgroundPath.addRoundRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundRadius, backgroundRadius, Path.Direction.CCW)
    canvas.drawPath(backgroundPath, paint)

    countPath.reset()
    // 绘制票数进度
    paint.color = if (isUserSelected) {
      selectedCountBackgroundColor
    } else {
      unselectedBackgroundColor
    }
    if (isShowSelectedCount) {
      val countWidth: Float = width * ratio * showCountProgress
      countPath.addRect(0f, 0f, countWidth, height.toFloat(), Path.Direction.CW)
      // 剪裁出一个圆角矩形
      canvas.clipPath(backgroundPath)
      canvas.drawPath(countPath, paint)
    }
  }
}