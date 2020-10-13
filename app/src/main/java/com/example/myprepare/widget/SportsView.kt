package com.example.myprepare.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.Utils

class SportsView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

  companion object {

    private val RING_WIDTH = Utils.dp2px(20f)
    private val RADIUS = Utils.dp2px(150f)
    private val CIRCLE_COLOR: Int = android.graphics.Color.parseColor("#90A4AE")
    private val HIGHLIGHT_COLOR: Int = android.graphics.Color.parseColor("#FF4081")
  }

  var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  var rect: Rect = Rect()
  var fontMetrics: Paint.FontMetrics = Paint.FontMetrics()

  init {
    paint.textSize = Utils.dp2px(100f)
//        paint.typeface = Typeface.createFromAsset(context?.assets, "Quicksand-Regular.ttf")
    paint.getFontMetrics(fontMetrics)
    paint.textAlign = Paint.Align.CENTER
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    // 绘制环
    paint.style = Paint.Style.STROKE
    paint.color = CIRCLE_COLOR
    paint.strokeWidth = RING_WIDTH
    canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), RADIUS, paint)

    // 绘制进度条
    paint.color = HIGHLIGHT_COLOR
    paint.strokeCap = Paint.Cap.ROUND
    canvas?.drawArc(
        width / 2 - RADIUS,
        height / 2 - RADIUS,
        width / 2 + RADIUS,
        height / 2 + RADIUS,
        -90f,
        225f,
        false,
        paint
    )

    paint.textSize = Utils.dp2px(100f)
    paint.style = Paint.Style.FILL
    paint.textAlign = Paint.Align.CENTER
    canvas?.drawText("abab", (width / 2).toFloat(), (height / 2).toFloat(), paint)
  }
}