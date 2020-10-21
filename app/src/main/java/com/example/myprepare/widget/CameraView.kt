package com.example.myprepare.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.R
import com.example.myprepare.dp
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt

class CameraView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

  companion object {

    private val BITMAP_SIZE = 150.dp
    private val BITMAP_PADDING = 100.dp
    private const val ROTATE = 40f
  }

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val bitmap: Bitmap = getAvatar(BITMAP_SIZE.toInt())
  private val clipped: Path = Path().apply {
    addCircle(
        BITMAP_PADDING + BITMAP_SIZE / 2f,
        BITMAP_PADDING + BITMAP_SIZE / 2f,
        BITMAP_SIZE / 2f,
        Path.Direction.CW
    )
  }

  private val camera: Camera = Camera()

  init {
    camera.rotateX(30f)
    camera.setLocation(0f, 0f, -2f * resources.displayMetrics.density)
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
  }

  override fun onDraw(canvas: Canvas) {
    // 上面的
    canvas.save()
    canvas.translate(
        BITMAP_PADDING + BITMAP_SIZE / 2f,
        BITMAP_PADDING + BITMAP_SIZE / 2f
    )
    canvas.rotate(-ROTATE)
    // 剪裁
    canvas.clipRect(
        (-BITMAP_SIZE * sqrt(2.toDouble()) / 2f).toFloat(),
        (-BITMAP_SIZE * sqrt(2.toDouble()) / 2f).toFloat(),
        (BITMAP_SIZE * sqrt(2.toDouble()) / 2f).toFloat(),
        0f
    )
    canvas.rotate(ROTATE)
    canvas.translate(
        -(BITMAP_PADDING + BITMAP_SIZE / 2f),
        -(BITMAP_PADDING + BITMAP_SIZE / 2f)
    )
    canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
    canvas.restore()

    // 画条线
    canvas.save()
    canvas.translate(
        BITMAP_PADDING + BITMAP_SIZE / 2f,
        BITMAP_PADDING + BITMAP_SIZE / 2f
    )
    canvas.rotate(-ROTATE)
    // 剪裁
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 3f.dp
    paint.color = Color.parseColor("#c9780c")
    canvas.drawLine(-BITMAP_SIZE, 0f, BITMAP_SIZE, 0f, paint)
    canvas.rotate(ROTATE)
    canvas.translate(
        -(BITMAP_PADDING + BITMAP_SIZE / 2f),
        -(BITMAP_PADDING + BITMAP_SIZE / 2f)
    )
    canvas.restore()

    // 下面的
    canvas.save()
    canvas.translate(
        BITMAP_PADDING + BITMAP_SIZE / 2f,
        BITMAP_PADDING + BITMAP_SIZE / 2f
    )
    canvas.rotate(-ROTATE)
    camera.applyToCanvas(canvas)
    // 剪裁
    canvas.clipRect(
        (-BITMAP_SIZE * 2.toDouble().pow(0.5) / 2f).toFloat(),
        0f,
        (BITMAP_SIZE * 2.toDouble().pow(0.5) / 2f).toFloat(),
        (BITMAP_SIZE * 2.toDouble().pow(0.5) / 2f).toFloat()
    )
    canvas.rotate(ROTATE)
    canvas.translate(
        -(BITMAP_PADDING + BITMAP_SIZE / 2f),
        -(BITMAP_PADDING + BITMAP_SIZE / 2f)
    )
    canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
    canvas.restore()
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