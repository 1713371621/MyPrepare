package com.example.myprepare.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.R
import com.example.myprepare.dp

class CameraView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    companion object {

        private val BITMAP_SIZE = 150.dp
        private val BITMAP_PADDING = 100.dp
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
//        addOval(
//          BITMAP_PADDING,
//          BITMAP_PADDING,
//          BITMAP_PADDING + BITMAP_SIZE,
//          BITMAP_PADDING + BITMAP_SIZE,
//          Path.Direction.CCW
//        )
    }

    private val camera: Camera = Camera()

    init {
        camera.rotateX(30f)
        camera.setLocation(0f, 0f, -8 * resources.displayMetrics.density)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {

//        canvas.clipPath(clipped)
        canvas.translate(
            BITMAP_PADDING + BITMAP_SIZE / 2f,
            BITMAP_PADDING + BITMAP_SIZE / 2f
        )
        camera.applyToCanvas(canvas)
        canvas.translate(
            -(BITMAP_PADDING + BITMAP_SIZE / 2f),
            -(BITMAP_PADDING + BITMAP_SIZE / 2f)
        )
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
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