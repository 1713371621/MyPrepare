package com.example.myprepare.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.Utils
import com.example.myprepare.Utils.dp2px
import com.orhanobut.logger.Logger

class DashBoardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

  companion object {

    private const val ANGLE = 120
    private val RADIUS: Float = 150f.dp2px()
    private val LENGTH: Float = 100f.dp2px()
  }

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val dash: Path = Path()
  private val pathDashPathEffect: PathDashPathEffect

  private val pathMeasure: PathMeasure
  private val posFloatArray = FloatArray(2)
  private val tanFloatArray = FloatArray(2)

  init {
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 2f.dp2px()

    dash.addRect(0f, 0f, 2f.dp2px(), 6f.dp2px(), Path.Direction.CW)
    val pathArc = Path()
    pathArc.addArc(
        (width / 2) - RADIUS, (height / 2) - RADIUS,
        (width / 2) + RADIUS, (height / 2) + RADIUS,
        90f + ANGLE / 2, 360f - ANGLE
    )
    pathMeasure = PathMeasure(pathArc, false)
    pathDashPathEffect = PathDashPathEffect(
        dash,
        (pathMeasure.length - 2f.dp2px()) / 20,
        0f,
        PathDashPathEffect.Style.ROTATE
    )

  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    canvas?.drawArc(
        (width / 2) - RADIUS, (height / 2) - RADIUS,
        (width / 2) + RADIUS, (height / 2) + RADIUS,
        90f + ANGLE / 2, 360f - ANGLE,
        false, paint
    )

    paint.pathEffect = pathDashPathEffect

    canvas?.drawArc(
        (width / 2) - RADIUS, (height / 2) - RADIUS,
        (width / 2) + RADIUS, (height / 2) + RADIUS,
        90f + ANGLE / 2, 360f - ANGLE,
        false, paint
    )
    paint.pathEffect = null

    pathMeasure.getPosTan(250f.dp2px() + 2f.dp2px(), posFloatArray, tanFloatArray)
    Logger.d(posFloatArray)
    Logger.d(tanFloatArray)

    canvas?.drawLine(
        width / 2f,
        height / 2f,
        width / 2 + posFloatArray[0],
        height / 2 + posFloatArray[1],
//            (cos(Math.toRadians(getAngleFromMark(5).toDouble()) / 2) * LENGTH + width / 2 - RADIUS).toFloat(),
//            (sin(Math.toRadians(getAngleFromMark(5).toDouble()) / 2) * LENGTH).toFloat(),
        paint
    )

  }

  private fun getAngleFromMark(mark: Int): Int {
    return 90 + ANGLE / 2 + (360 - ANGLE) / 20 * mark
  }
}