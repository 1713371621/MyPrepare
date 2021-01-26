package com.example.myprepare.view_test.left_age

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.myprepare.R
import com.example.myprepare.dp
import kotlin.collections.ArrayList

class ScrollerNumberView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

  companion object {
    private const val TAG = "ScrollerNumberView"
    private const val PAINT_ALPHA = 255
  }

  // 这两个都默认大于等于0, 不考虑小于零的情况
  var number: Int = 8
    set(value) {
      scrollerAnimator.cancel()
      stringBuilder.clear()
      newNumberList.clear()
      oldNumberList.clear()
      field = value
      progress = 0f

      totalNumber = processingNumber(field, oldNumberList, newNumberList)

      if (newNumberList.size == oldNumberList.size) {
        sameNumberIndex = getSameNumberIndex(oldNumberList, newNumberList, newNumberList.size)
        if (sameNumberIndex < newNumberList.size && sameNumberIndex >= 0) {
          // 有相同高位数字
          sameText = getSameNumber(newNumberList, sameNumberIndex)
        } else {
          sameNumberIndex = -1
          sameText = ""
        }
      } else {
        sameNumberIndex = -1
        sameText = ""
      }

      oldText = getDiffNumber(oldNumberList, sameNumberIndex)
      newText = getDiffNumber(newNumberList, sameNumberIndex)
      invalidate()
    }

  private val oldNumberList: MutableList<String> = ArrayList()
  private val newNumberList: MutableList<String> = ArrayList()

  private val textPaint: TextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
  private val fontMetrics: Paint.FontMetrics

  private val textRect: Rect = Rect()

  private val scrollerAnimator: ValueAnimator by lazy {
    ObjectAnimator.ofFloat(this, "progress", 0f, 1f)
  }

  private val hindAnimator: ValueAnimator by lazy {
    ObjectAnimator.ofFloat(this, "paintAlphaProgress", 1f, 0f)
  }

  private val showAnimator: ValueAnimator by lazy {
    ObjectAnimator.ofFloat(this, "paintAlphaProgress", 0f, 1f)
  }

  private var paintAlphaProgress: Float = 1f
    set(value) {
      field = value
      invalidate()
    }

  private var progress: Float = 0f
    set(value) {
      field = value
      invalidate()
    }

  private var startScrollerTextAlpha: Float = 1f

  private val textSize: Float
  private val singleTextWidth: Float
  private val textSpacing: Float

  private var stringBuilder: StringBuilder = StringBuilder()

  private var totalNumber: String = ""
  private var sameText: String = ""
  private var oldText: String = ""
  private var newText: String = ""

  private var sameNumberIndex = -1

  init {
    val typedArray: TypedArray =
      context.obtainStyledAttributes(attrs, R.styleable.ScrollerNumberView)
    textSize = typedArray.getDimension(R.styleable.ScrollerNumberView_textSize, 14.dp)
    singleTextWidth = typedArray.getDimension(R.styleable.ScrollerNumberView_singleTextWidth, 7.dp)
    textSpacing = typedArray.getDimension(R.styleable.ScrollerNumberView_textSpacing, 1.dp)

    textPaint.textSize = textSize
    textPaint.color =
      typedArray.getColor(R.styleable.ScrollerNumberView_textColor, context.getColor(R.color.white))
    textPaint.alpha = (PAINT_ALPHA * paintAlphaProgress).toInt()

    fontMetrics = Paint.FontMetrics()
    textPaint.getFontMetrics(fontMetrics)

    totalNumber = processingNumber(number, oldNumberList, newNumberList)

    if (newNumberList.size == oldNumberList.size) {
      sameNumberIndex = getSameNumberIndex(oldNumberList, newNumberList, newNumberList.size)
      if (sameNumberIndex < newNumberList.size && sameNumberIndex >= 0) {
        sameText = getSameNumber(newNumberList, sameNumberIndex)
      } else {
        sameNumberIndex = -1
        sameText = ""
      }
    } else {
      sameNumberIndex = -1
      sameText = ""
    }

    oldText = getDiffNumber(oldNumberList, sameNumberIndex)
    newText = getDiffNumber(newNumberList, sameNumberIndex)

    typedArray.recycle()
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
//    animator.start()
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    scrollerAnimator.cancel()
    hindAnimator.cancel()
    showAnimator.cancel()
  }


  override fun onDraw(canvas: Canvas) {
    stringBuilder.clear()

    val text = "天"
    textPaint.textSize = 11f.dp
    textPaint.alpha = (PAINT_ALPHA * paintAlphaProgress).toInt()
    textPaint.getTextBounds(text, 0, text.length, textRect)

    canvas.drawText(
      text,
      width - textRect.width().toFloat(),
      (height - (fontMetrics.top + fontMetrics.bottom)) / 2f,
      textPaint
    )
    // 剩余宽度
    val residueWidth = width - textRect.width()

    textPaint.textSize = 14f.dp

    var textWidth = 0
    var currentTextWidth = 0
    if (newNumberList.size == oldNumberList.size) {
      if (sameNumberIndex < newNumberList.size && sameNumberIndex != -1) {
        Log.d(TAG, "onDraw: has same number, sameNumberIndex = $sameNumberIndex")
        // 有相同高位数字
        textPaint.getTextBounds(totalNumber, 0, totalNumber.length, textRect)
        textWidth = textRect.width()

        if (sameText.isNotBlank()) {
          for (i: Int in sameText.indices) {
            canvas.drawText(
              sameText[i].toString(),
              (residueWidth - textWidth) / 2f + currentTextWidth - 3,
              (height - (fontMetrics.top + fontMetrics.bottom)) / 2f,
              textPaint
            )
            currentTextWidth += singleTextWidth.toInt() + textSpacing.toInt()
          }
        }
      } else if (Integer.parseInt(totalNumber) <= 10) {
        // 个位时要加上0来显示
        textPaint.getTextBounds("0 $totalNumber", 0, "0 $totalNumber".length, textRect)
        textWidth = textRect.width()
        val addSameText = "0 "
        for (i: Int in addSameText.indices) {
          canvas.drawText(
            addSameText[i].toString(),
            (residueWidth - textWidth) / 2f + currentTextWidth - 3,
            (height - (fontMetrics.top + fontMetrics.bottom)) / 2f,
            textPaint
          )
          currentTextWidth += singleTextWidth.toInt() - 2.2f.dp.toInt()
        }
      }
    }

    // 绘制旧的不同数字
    val oldTextWidth = if (textWidth == 0) {
      textPaint.getTextBounds(oldText, 0, oldText.length, textRect)
      textRect.width()
    } else {
      textWidth
    }
    Log.d(TAG, "onDraw: oldText = $oldText, newText = $newText")

    var oldTextCurrentWidth = 0f
    if (totalNumber == "10") {
      textPaint.getTextBounds("0 $oldText", 0, "0 $oldText".length, textRect)
      val oldTextWidth1 = textRect.width()
      // 个位时要加上0来显示
      textPaint.alpha = (PAINT_ALPHA * paintAlphaProgress * (1 - progress)).toInt()
      for (i: Int in "0 $oldText".indices) {
        canvas.drawText(
          "0 $oldText"[i].toString(),
          (residueWidth - oldTextWidth1) / 2f + currentTextWidth + oldTextCurrentWidth - 3,
          (height - (fontMetrics.top + fontMetrics.bottom)) / 2f + (fontMetrics.top + fontMetrics.bottom) * progress,
          textPaint
        )
        oldTextCurrentWidth += singleTextWidth.toInt() - 2.2f.dp.toInt()
      }
    } else if (oldText.isNotBlank()) {
      textPaint.alpha = (PAINT_ALPHA * paintAlphaProgress * (1 - progress)).toInt()
      for (i: Int in oldText.indices) {
        canvas.drawText(
          oldText[i].toString(),
          (residueWidth - oldTextWidth) / 2f + currentTextWidth + oldTextCurrentWidth - 3,
          (height - (fontMetrics.top + fontMetrics.bottom)) / 2f + (fontMetrics.top + fontMetrics.bottom) * progress,
          textPaint
        )
        oldTextCurrentWidth += singleTextWidth.toInt() + textSpacing.toInt()
      }
    }

    // 绘制新的不同数字
    val newTextWidth = if (textWidth == 0) {
      textPaint.getTextBounds(newText, 0, newText.length, textRect)
      textRect.width()
    } else {
      textWidth
    }

    var newTextCurrentWidth = 0f
    if (newText.isNotBlank()) {
      textPaint.alpha = (PAINT_ALPHA * paintAlphaProgress * progress).toInt()
      for (i: Int in newText.indices) {
        canvas.drawText(
          newText[i].toString(),
          (residueWidth - newTextWidth) / 2f + currentTextWidth + newTextCurrentWidth - 3,
          (height - (fontMetrics.top + fontMetrics.bottom)) / 2f + (fontMetrics.top + fontMetrics.bottom) * (progress - 1),
          textPaint
        )
        newTextCurrentWidth += singleTextWidth.toInt() + textSpacing.toInt()
      }
    }
  }

  fun startScrollerAnimator(startAlpha: Float, duration: Long, startDelay: Long) {
    if (scrollerAnimator.isRunning) {
      scrollerAnimator.cancel()
    }
    startScrollerTextAlpha = startAlpha

    scrollerAnimator.duration = duration
    scrollerAnimator.startDelay = startDelay
    scrollerAnimator.start()
  }

  fun startHindAnimator(duration: Long, startDelay: Long) {
    if (hindAnimator.isRunning) {
      hindAnimator.cancel()
    }
    hindAnimator.setFloatValues(1f, 0f)
    hindAnimator.duration = duration
    hindAnimator.startDelay = startDelay
    startScrollerTextAlpha = 1f
    hindAnimator.start()
  }

  fun startShowAnimator(alphaProgress: Float, duration: Long, startDelay: Long) {
    if (showAnimator.isRunning) {
      showAnimator.cancel()
    }
    showAnimator.setFloatValues(0f, alphaProgress)
    showAnimator.duration = duration
    showAnimator.startDelay = startDelay
    showAnimator.start()
  }

  private fun getDiffNumber(list: MutableList<String>, index: Int): String {
    stringBuilder.clear()
    if (index < 0) {
      list.forEach {
        stringBuilder.append(it)
      }
    } else {
      for (i: Int in index + 1 until list.size) {
        stringBuilder.append(list[i])
      }
    }
    return stringBuilder.toString()
  }

  private fun getSameNumber(newList: MutableList<String>, index: Int): String {
    stringBuilder.clear()
    for (i: Int in 0..index) {
      stringBuilder.append(newList[i])
    }
    return stringBuilder.toString()
  }

  private fun getSameNumberIndex(
    oldList: MutableList<String>,
    newList: MutableList<String>,
    size: Int
  ): Int {
    for (i: Int in 0 until size) {
      if (oldList[i] != newList[i]) {
        return i - 1
      }
    }
    return -1
  }

  private fun processingNumber(
    number: Int,
    oldList: MutableList<String>,
    newList: MutableList<String>
  ): String {
    val newNumber: Int
    val oldNumber: Int
    if (number <= 0) {
      newNumber = 1
      oldNumber = 0
    } else {
      newNumber = number
      oldNumber = number - 1
    }
    splitNumber(oldNumber, oldList)
    splitNumber(newNumber, newList)
    return newNumber.toString()
  }

  /**
   * 把数字拆了
   */
  private fun splitNumber(number: Int, list: MutableList<String>) {
    number.toString().forEach {
      list.add(it.toString())
    }
  }
}