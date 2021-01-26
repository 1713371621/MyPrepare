package com.example.myprepare.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.example.myprepare.R
import com.example.myprepare.dp

class MaterialEditText(context: Context, attrs: AttributeSet) :
  androidx.appcompat.widget.AppCompatEditText(context, attrs) {

  companion object {

    val TEXT_SIZE: Float = 12.dp
    val TEXT_MARGIN: Float = 8.dp
    val HORIZONTAL_OFFSET = 5.dp
    val VERTICAL_OFFSET = 23.dp
  }

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private var fontMetrics: Paint.FontMetrics = Paint.FontMetrics()
  private var floatingLabelShown = false

  var useFloatingLabel: Boolean = false
    set(value) {
      if (field != value) {
        field = value
        if (field) {
          setPadding(
            paddingLeft,
            (paddingTop + TEXT_SIZE + TEXT_MARGIN).toInt(),
            paddingRight,
            paddingBottom
          )
        } else {
          setPadding(
            paddingLeft,
            (paddingTop - TEXT_SIZE - TEXT_MARGIN).toInt(),
            paddingRight,
            paddingBottom
          )
        }
      }

    }

  var floatingLabelFraction: Float = 0f
    set(value) {
      field = value
      invalidate()
    }

  private val animator: ValueAnimator by lazy {
    ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
  }

  init {
    paint.textSize = TEXT_SIZE

    // val typedArray: TypedArray = context.obtainStyledAttributes(attrs, intArrayOf(R.attr.useFloatLabel))
    // useFloatingLabel = typedArray.getBoolean(0, true)
    val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
    useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
    typedArray.recycle()
    getPaint().getFontMetrics(fontMetrics)
  }

  override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {

    if (floatingLabelShown && text.isNullOrEmpty()) {
      floatingLabelShown = false
      animator.reverse()
    } else if (!floatingLabelShown && !text.isNullOrEmpty()) {
      floatingLabelShown = true
      animator.start()
    }
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    paint.alpha = (floatingLabelFraction * 0xff).toInt()
    val currentVerticalValue: Float =
      VERTICAL_OFFSET + (fontMetrics.bottom - fontMetrics.top) * (1 - floatingLabelFraction)
    canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentVerticalValue, paint)
  }
}