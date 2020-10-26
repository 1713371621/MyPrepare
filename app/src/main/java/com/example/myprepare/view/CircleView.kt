package com.example.myprepare.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.dp

class CircleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

  var radius = 30.dp
    set(value) {
      field = value
      invalidate()
    }

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    style = Paint.Style.STROKE
    strokeWidth = 2.dp
    color = Color.parseColor("#bd68a1")
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawCircle(width / 2f, height / 2f, radius, paint)
  }
}