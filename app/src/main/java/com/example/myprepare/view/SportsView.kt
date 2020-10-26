package com.example.myprepare.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.Utils.dp2px
import com.example.myprepare.dp

class SportsView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
  
  companion object {
    
    private val RING_WIDTH = 20f.dp2px()
    private val RADIUS = 100f.dp2px()
    private val CIRCLE_COLOR: Int = android.graphics.Color.parseColor("#90A4AE")
    private val HIGHLIGHT_COLOR: Int = android.graphics.Color.parseColor("#FF4081")
  }
  
  private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private var fontMetrics: Paint.FontMetrics = Paint.FontMetrics()
  private val bounds: Rect = Rect()
  
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
    
    paint.style = Paint.Style.FILL
    
    paint.textSize = 50f.dp2px()
    paint.textAlign = Paint.Align.CENTER
    paint.getFontMetrics(fontMetrics)
    paint.getTextBounds("aaaa", 0, "aaaa".length, bounds)
    canvas?.drawText(
      "aaaa",
      width / 2f,
      height / 2f - ((fontMetrics.ascent + fontMetrics.descent) / 2f),
//        height / 2f - ((bounds.top + bounds.bottom) / 2f),
      paint
    )
    
    // 绘制文字2
    paint.textSize = 100.dp
    paint.textAlign = Paint.Align.LEFT
    paint.getFontMetrics(fontMetrics)
    paint.getTextBounds("apab", 0, "apab".length, bounds)
    canvas?.drawText("apab", 0f - bounds.left, 0f - fontMetrics.top, paint)
    
    // 绘制文字3
    paint.textSize = 40.dp
    paint.textAlign = Paint.Align.LEFT
    paint.getFontMetrics(fontMetrics)
    paint.getTextBounds("ippb", 0, "ippb".length, bounds)
    canvas?.drawText("ippb", 0f - bounds.left, 0f - fontMetrics.top, paint)
  }
}