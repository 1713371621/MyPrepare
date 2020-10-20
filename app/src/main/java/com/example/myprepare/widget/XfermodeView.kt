package com.example.myprepare.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.dp

class XfermodeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds: RectF
    private val xfermode: Xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)

    private val circleBitmap: Bitmap =
        Bitmap.createBitmap((3 * RADIUS).toInt(), (3f * RADIUS).toInt(), Bitmap.Config.ARGB_8888)

    private val squareBitmap: Bitmap =
        Bitmap.createBitmap((3 * RADIUS).toInt(), (3f * RADIUS).toInt(), Bitmap.Config.ARGB_8888)


    companion object {
        private val RADIUS: Float = 80f.dp
    }

    init {
        bounds = RectF(
            width / 2f - RADIUS,
            height / 2f - RADIUS * 2,
            width / 2f + RADIUS * 2,
            height / 2f + RADIUS
        )

        val canvas = Canvas()

        canvas.setBitmap(circleBitmap)
        paint.color = Color.parseColor("#ff6b81")
        canvas.drawCircle(2 * RADIUS, RADIUS, RADIUS, paint)
        canvas.setBitmap(squareBitmap)

        paint.color = Color.parseColor("#5352ed")
        canvas.drawRect(
            0f,
            RADIUS,
            2 * RADIUS,
            3 * RADIUS,
            paint
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        bounds.set(
            width / 2f - RADIUS,
            height / 2f - RADIUS * 2,
            width / 2f + RADIUS * 2,
            height / 2f + RADIUS
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL

        val count: Int? = canvas?.saveLayer(bounds, paint)
        canvas?.drawBitmap(circleBitmap, width / 2f - RADIUS, height / 2f - RADIUS * 2, paint)
//        paint.color = Color.parseColor("#ff6b81")
//        canvas?.drawCircle(width / 2f + RADIUS, height / 2f - RADIUS, RADIUS, paint)
        paint.xfermode = xfermode
//        paint.color = Color.parseColor("#5352ed")
//        canvas?.drawRect(
//            width / 2f - RADIUS,
//            height / 2f + RADIUS,
//            width / 2f + RADIUS,
//            height / 2f - RADIUS,
//            paint
//        )
        canvas?.drawBitmap(squareBitmap, width / 2f - RADIUS, height / 2f - RADIUS * 2, paint)
        paint.xfermode = null
        if (count != null) {
            canvas.restoreToCount(count)
        }

        paint.style = Paint.Style.STROKE
        paint.color = Color.parseColor("#e74c3c")
        canvas?.drawRect(bounds, paint)

        paint.color = Color.parseColor("#1abc9c")
        paint.strokeWidth = 3f.dp
        canvas?.drawLine(
            width / 2f - 100f.dp,
            height / 2f,
            width / 2f + 100f.dp,
            height / 2f,
            paint
        )

        canvas?.drawLine(
            width / 2f,
            height / 2f - 100f.dp,
            width / 2f,
            height / 2f + 100f.dp,
            paint
        )
    }
}