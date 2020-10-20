package com.example.myprepare.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.Utils.dp2px
import com.example.myprepare.dp
import kotlin.math.cos
import kotlin.math.sin

class DashBoardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

  companion object {

    private const val ANGLE = 120
    private val RADIUS: Float = 100f.dp2px()
    private val LENGTH: Float = RADIUS * 2f / 3f
  }

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val dash: Path = Path()
  private val arcPath: Path = Path()
  private val pathDashPathEffect: PathDashPathEffect

  private val pathMeasure: PathMeasure
  private val posFloatArray = FloatArray(2)
  private val tanFloatArray = FloatArray(2)

  private val DASH_WIDTH = 2f.dp
  private val DASH_LENGTH = 5f.dp

  init {
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 2f.dp

    dash.addRect(0f, 0f, 2f.dp2px(), 6f.dp2px(), Path.Direction.CW)
    arcPath.addArc(
        (width / 2) - RADIUS, (height / 2) - RADIUS,
        (width / 2) + RADIUS, (height / 2) + RADIUS,
        90f + ANGLE / 2, 360f - ANGLE
    )
    pathMeasure = PathMeasure(arcPath, false)
    pathDashPathEffect = PathDashPathEffect(
        dash,
        (pathMeasure.length - 2f.dp2px()) / 20,
        0f,
        PathDashPathEffect.Style.ROTATE
    )
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    arcPath.reset()
    arcPath.addArc(
        (width / 2) - RADIUS, (height / 2) - RADIUS,
        (width / 2) + RADIUS, (height / 2) + RADIUS,
        90f + ANGLE / 2, 360f - ANGLE
    )
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    canvas?.drawPath(arcPath, paint)
    paint.pathEffect = pathDashPathEffect
    canvas?.drawPath(arcPath, paint)
    paint.pathEffect = null

    val index = 7
    val stopX: Float = width / 2 + LENGTH * cos(
        Math.toRadians(
            (ANGLE / 2f + 90 + (360 - ANGLE) / 20f * index).toDouble()
        )
    ).toFloat()
    val stopY: Float = height / 2 + LENGTH * sin(
        Math.toRadians(
            (ANGLE / 2f + 90 + (360 - ANGLE) / 20f * index).toDouble()
        )
    ).toFloat()
    canvas?.drawLine(
        width / 2f,
        height / 2f,
        stopX,
        stopY,
        paint
    )
  }

  private fun getAngleFromMark(mark: Int): Int {
    return 90 + ANGLE / 2 + (360 - ANGLE) / 20 * mark
  }
}