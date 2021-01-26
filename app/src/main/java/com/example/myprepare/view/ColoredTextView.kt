package com.example.myprepare.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.myprepare.dp
import kotlin.random.Random

class ColoredTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

  private val TEXT_SIZES = intArrayOf(15, 22, 28)
  private val COLORS = intArrayOf(
    Color.parseColor("#f52443"),
    Color.parseColor("#c66f35"),
    Color.parseColor("#eddd9e"),
    Color.parseColor("#1e3124"),
    Color.parseColor("#3c374a"),
  )

  private val x_padding: Int = 16.dp.toInt()
  private val y_padding: Int = 8.dp.toInt()
  private val corner_radius = 4.dp

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

  init {
    setTextColor(Color.WHITE)
    textSize = TEXT_SIZES[Random.nextInt(3)].toFloat()
    paint.color = COLORS[Random.nextInt(COLORS.size)]
    setPadding(x_padding, y_padding, x_padding, y_padding)
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawRoundRect(
      0f,
      0f,
      width.toFloat(),
      height.toFloat(),
      corner_radius,
      corner_radius,
      paint
    )
    super.onDraw(canvas)
  }

}