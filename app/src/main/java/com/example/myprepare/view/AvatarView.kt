package com.example.myprepare.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.R
import com.example.myprepare.Utils.dp2px
import com.example.myprepare.dp

class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

  companion object {

    private val WIDTH: Float = 140f.dp
    private val PADDING: Float = 40f.dp2px()
    private val EDGE_WIDTH: Float = 2f.dp2px()
  }

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val bitmap: Bitmap
  private val xfermode: Xfermode
  private val savedArea: RectF = RectF()

  init {
    bitmap = getAvatar(WIDTH.toInt())
    xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    setLayerType(LAYER_TYPE_HARDWARE, null)
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

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    savedArea.set(
      PADDING,
      PADDING,
      PADDING + WIDTH,
      PADDING + WIDTH
    )
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    // 离屏缓冲
    /*val saved: Int? = canvas?.saveLayer(savedArea, paint)*/
    canvas?.drawOval(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH, paint)
    paint.xfermode = xfermode
    canvas?.drawBitmap(bitmap, PADDING, PADDING, paint)
    paint.xfermode = null
    /*if (saved != null) {
      canvas.restoreToCount(saved)
    }*/

    paint.color = Color.parseColor("#a4b0be")
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 3.dp
    canvas?.drawOval(
      PADDING - EDGE_WIDTH,
      PADDING - EDGE_WIDTH,
      PADDING + WIDTH + EDGE_WIDTH,
      PADDING + WIDTH + EDGE_WIDTH,
      paint
    )
  }
}