package com.example.myprepare.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import androidx.core.graphics.toColorInt
import com.example.myprepare.dp

class MeshDrawable : Drawable() {
  
  var interval: Float = 50.dp
  
  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    style = Paint.Style.STROKE
    strokeWidth = 3.dp
//    color = Color.parseColor("#ac3b22")
    color = "#ac3b22".toColorInt()
  }
  
  override fun draw(canvas: Canvas) {
    var drawLineX: Float = bounds.left.toFloat()
    
    while (drawLineX <= bounds.right) {
      canvas.drawLine(
        drawLineX,
        bounds.top.toFloat(),
        drawLineX,
        bounds.bottom.toFloat(),
        paint
      )
      drawLineX += interval
    }
    
    var drawLineY: Float = bounds.top.toFloat()
    
    while (drawLineY <= bounds.bottom) {
      canvas.drawLine(
        bounds.left.toFloat(),
        drawLineY,
        bounds.right.toFloat(),
        drawLineY,
        paint
      )
      drawLineY += interval
    }
  }
  
  override fun setAlpha(alpha: Int) {
    paint.alpha = alpha
  }
  
  override fun getAlpha(): Int {
    return paint.alpha
  }
  
  override fun setColorFilter(colorFilter: ColorFilter?) {
    paint.colorFilter = colorFilter
  }
  
  override fun getColorFilter(): ColorFilter? {
    return paint.colorFilter
  }
  
  override fun getOpacity(): Int {
    return when (paint.alpha) {
      0 -> PixelFormat.TRANSPARENT
      0xff -> PixelFormat.OPAQUE
      else -> PixelFormat.TRANSLUCENT
    }
  }
}