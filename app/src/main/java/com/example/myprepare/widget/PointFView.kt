package com.example.myprepare.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.dp

class PointFView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  var point: PointF = PointF(0f, 0f)
    set(value) {
      field = value
      invalidate()
    }

  init {
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 20.dp
    paint.strokeCap = Paint.Cap.ROUND
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawPoint(point.x, point.y, paint)
  }
}