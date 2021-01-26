package com.example.myprepare.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.Utils.dp2px
import kotlin.math.cos
import kotlin.math.sin

class PieChartView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

  companion object {

    private val RADIUS: Float = 70f.dp2px()
    private val LENGTH: Float = 5f.dp2px()
  }

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val bounds: RectF = RectF()

  private val angles: FloatArray = floatArrayOf(60f, 30f, 80f, 35f, 55f, 100f)
  private val colors: IntArray = intArrayOf(
    Color.parseColor("#eccc68"),
    Color.parseColor("#ff7f50"),
    Color.parseColor("#ff6b81"),
    Color.parseColor("#7bed9f"),
    Color.parseColor("#70a1ff"),
    Color.parseColor("#5352ed")
  )

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)

    bounds.set(width / 2 - RADIUS, height / 2 - RADIUS, width / 2 + RADIUS, height / 2 + RADIUS)
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    var currentAngle = 0f
    for ((index, angle) in angles.withIndex()) {
      canvas?.save()

      paint.color = colors[index]
      canvas?.translate(
        (cos(Math.toRadians(currentAngle + angle / 2.00)) * LENGTH).toFloat(),
        (sin(Math.toRadians(currentAngle + angle / 2.00)) * LENGTH).toFloat(),
      )
      canvas?.drawArc(bounds, currentAngle, angle, true, paint)

      currentAngle += angle

      canvas?.restore()
    }
  }
}