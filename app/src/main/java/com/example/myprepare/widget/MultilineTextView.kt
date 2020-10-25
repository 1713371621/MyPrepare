package com.example.myprepare.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.myprepare.R
import com.example.myprepare.dp

class MultilineTextView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
  
  // lorem ipsum
  val text =
    "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."
  
  private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
    textSize = 16.dp
  }
  
  private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
  
  private val bitmap: Bitmap = getAvatar(IMAGE_SIZE.toInt())
  
  private val fontMetrics = Paint.FontMetrics()
  
  companion object {
    
    private const val TAG = "MultilineTextView"
    private val IMAGE_SIZE = 100.dp
  }
  
  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
  }
  
  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
//        val staticLayout = StaticLayout.Builder.obtain(text, 0, text.length, textPaint, width)
//            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
//            .setLineSpacing(0f, 1f)
//            .setIncludePad(false)
//            .build()
//        staticLayout.draw(canvas)
    
    canvas.drawBitmap(bitmap, width - IMAGE_SIZE, height / 2f - IMAGE_SIZE / 2f, paint)
    val measuredWidth: FloatArray = floatArrayOf(0f)
    
    paint.textSize = 16.dp
    paint.getFontMetrics(fontMetrics)
    var start = 0
    var count: Int
    var verticalOffset = -fontMetrics.top
    
    var maxWidth: Float
    
    while (start < text.length) {
      
      maxWidth = if ((verticalOffset + fontMetrics.bottom) < (height / 2f - IMAGE_SIZE / 2f) ||
        (verticalOffset + fontMetrics.top) > (height / 2f + IMAGE_SIZE / 2f)
      ) {
        width.toFloat()
      } else {
        width.toFloat() - IMAGE_SIZE
      }
      count = paint.breakText(
        text,
        start,
        text.length,
        true,
        maxWidth, measuredWidth
      )
      
      Log.d(TAG, "onDraw: count = $count, ${text[start + count - 1]}")
      
      canvas.drawText(text, start, start + count, 0f, verticalOffset, paint)
      
      start += count
      verticalOffset += paint.fontSpacing
    }
  }
  
  private fun getAvatar(avatarWidth: Int): Bitmap {
    val options: BitmapFactory.Options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, R.drawable.batman, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = avatarWidth
    return BitmapFactory.decodeResource(resources, R.drawable.batman, options)
  }
}