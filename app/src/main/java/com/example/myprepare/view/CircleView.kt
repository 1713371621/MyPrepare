package com.example.myprepare.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.dp
import kotlin.math.min
import kotlin.math.roundToInt

class CircleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
  
  var padding: Float = 30.dp
  var radius = 100.dp
    set(value) {
      field = value
      invalidate()
    }
  
  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    style = Paint.Style.STROKE
    strokeWidth = 2.dp
    color = Color.parseColor("#bd68a1")
  }
  
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    val size: Int = ((radius + padding) * 2).roundToInt()
    
    /*val widthMeasureSpecMode = MeasureSpec.getMode(widthMeasureSpec)
    val widthMeasureSpecSize = MeasureSpec.getSize(widthMeasureSpec)
    val widthSize = when (widthMeasureSpecMode) {
      MeasureSpec.AT_MOST -> min(size, widthMeasureSpecSize)
      MeasureSpec.EXACTLY -> widthMeasureSpecSize
      MeasureSpec.UNSPECIFIED -> size
      else -> size
    }*/
    val widthSize = resolveSize(size, widthMeasureSpec)
  
    /*val heightMeasureSpecMode = MeasureSpec.getMode(heightMeasureSpec)
    val heightMeasureSpecSize = MeasureSpec.getSize(heightMeasureSpec)
    val heightSize = when (heightMeasureSpecMode) {
      MeasureSpec.AT_MOST -> min(size, heightMeasureSpecSize)
      MeasureSpec.EXACTLY -> heightMeasureSpecSize
      MeasureSpec.UNSPECIFIED -> size
      else -> size
    }*/
    val heightSize = resolveSize(size, heightMeasureSpec)
  
    setMeasuredDimension(widthSize, heightSize)
  }
  
  override fun onDraw(canvas: Canvas) {
    canvas.drawCircle(padding + radius, padding + radius, radius, paint)
  }
}