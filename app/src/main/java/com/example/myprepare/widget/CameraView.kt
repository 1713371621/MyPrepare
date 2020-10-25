package com.example.myprepare.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.example.myprepare.R
import com.example.myprepare.dp
import kotlin.math.pow
import kotlin.math.sqrt

class CameraView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

  private val bitmapSize = 150.dp
  private val bitmapPadding = 100.dp

  var topFlip = 0f
    set(value) {
      field = value
      invalidate()
    }
  var bottomFlip = 0f
    set(value) {
      field = value
      invalidate()
    }
  var flipRotation = 0f
    set(value) {
      field = value
      invalidate()
    }

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val bitmap: Bitmap = getAvatar(bitmapSize.toInt())
  private val clipped: Path = Path().apply {
    addCircle(
        bitmapPadding + bitmapSize / 2f,
        bitmapPadding + bitmapSize / 2f,
        bitmapSize / 2f,
        Path.Direction.CW
    )
  }

  private val camera: Camera = Camera()

  init {
    camera.setLocation(0f, 0f, -8f * resources.displayMetrics.density)
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
  }

  override fun onDraw(canvas: Canvas) {
    // 上面的
    canvas.save()
    canvas.translate(
        bitmapPadding + bitmapSize / 2f,
        bitmapPadding + bitmapSize / 2f
    )
    canvas.rotate(-flipRotation)
    // camera操作
    camera.save()
    camera.rotateX(topFlip)
    camera.applyToCanvas(canvas)
    camera.restore()
    // 剪裁
    canvas.clipRect(
        (-bitmapSize * sqrt(2.toDouble()) / 2f).toFloat(),
        (-bitmapSize * sqrt(2.toDouble()) / 2f).toFloat(),
        (bitmapSize * sqrt(2.toDouble()) / 2f).toFloat(),
        0f
    )
    canvas.rotate(flipRotation)
    canvas.translate(
        -(bitmapPadding + bitmapSize / 2f),
        -(bitmapPadding + bitmapSize / 2f)
    )
    canvas.drawBitmap(bitmap, bitmapPadding, bitmapPadding, paint)
    canvas.restore()

    // 画条线
    canvas.save()
    canvas.translate(
        bitmapPadding + bitmapSize / 2f,
        bitmapPadding + bitmapSize / 2f
    )
    canvas.rotate(-flipRotation)
    // 剪裁
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 3f.dp
    paint.color = Color.parseColor("#c9780c")
    canvas.drawLine(-bitmapSize, 0f, bitmapSize, 0f, paint)
    canvas.rotate(flipRotation)
    canvas.translate(
        -(bitmapPadding + bitmapSize / 2f),
        -(bitmapPadding + bitmapSize / 2f)
    )
    canvas.restore()

    // 下面的
    canvas.withSave {
      // 同canvas.save
      // ...
      // canvas.restore
      canvas.translate(
          bitmapPadding + bitmapSize / 2f,
          bitmapPadding + bitmapSize / 2f
      )
      canvas.rotate(-flipRotation)
      // camera操作
      camera.save()
      camera.rotateX(bottomFlip)
      camera.applyToCanvas(canvas)
      camera.restore()
      // 剪裁
      canvas.clipRect(
          (-bitmapSize * 2.toDouble().pow(0.5) / 2f).toFloat(),
          0f,
          (bitmapSize * 2.toDouble().pow(0.5) / 2f).toFloat(),
          (bitmapSize * 2.toDouble().pow(0.5) / 2f).toFloat()
      )
      canvas.rotate(flipRotation)
      canvas.translate(
          -(bitmapPadding + bitmapSize / 2f),
          -(bitmapPadding + bitmapSize / 2f)
      )
      canvas.drawBitmap(bitmap, bitmapPadding, bitmapPadding, paint)
    }
  }

  /**
   * [avatarWidth]: 想要的大小
   * val options: BitmapFactory.Options = BitmapFactory.Options()
   * options.inJustDecodeBounds = true
   * BitmapFactory.decodeResource(resources, R.drawable.batman, options)
   * options.inJustDecodeBounds = false
   * options.inDensity = options.outWidth
   * options.inTargetDensity = avatarWidth
   * @return BitmapFactory.decodeResource(resources, R.drawable.batman, options)
   * */
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