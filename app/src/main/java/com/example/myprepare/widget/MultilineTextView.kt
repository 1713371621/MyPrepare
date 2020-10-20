package com.example.myprepare.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.R
import com.example.myprepare.dp

class MultilineTextView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    // lorem ipsum
    val text =
        "这种事实对本人来说意义重大,相信对这个世界也是有一定意义的.了解清楚lorem ipsum到底是一种怎么样的存在,是解决" +
                "一切问题的关键.黑塞在不经意间这样说过,有勇气承担命运这才是英雄好汉.这启发了我,了解清楚" +
                "lorem ipsum到底是一种怎么样的存在,是解决一切问题的关键.拉罗什福科在不经意间这样说过,我们唯一不会" +
                "改正的缺点是软弱.这似乎解答了我的疑惑.本人也是经过了深思熟虑,在每个日日夜夜思考这个问题.一般来说," +
                "我们不得不面对一个非常尴尬的事实,那就是,lorem ipsum的发生,到底需要如何做到,不lorem ipsum的" +
                "发生,又会如何产生.经过上述讨论,达·芬奇说过一句富有哲理的话,大胆和坚定的决心能够抵得上武器的精良." +
                "这启发了我,而这些并不是完全重要,更加重要的问题是,我们都知道,只要有意义,那么就必须慎重考虑." +
                "了解清楚lorem ipsum到底是一种怎么样的存在,是解决一切问题的关键.就我个人来说,lorem ipsum对我" +
                "的意义,不能不说非常重大.带着这些问题,我们来审视一下lorem ipsum.一般来说,总结的来说,既然如此.\n"

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap: Bitmap = getAvatar(100.dp.toInt())

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

        canvas.drawBitmap(bitmap,width - 100.dp, 150f, paint )
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