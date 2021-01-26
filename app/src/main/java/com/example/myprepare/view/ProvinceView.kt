package com.example.myprepare.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.myprepare.dp

class ProvinceView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

  companion object {

    val provinces: List<String> = listOf(
      "北京市",
      "天津市",
      "上海市",
      "南京市",
      "哈尔滨市",
      "辽宁省",
      "台湾省",
      "海南市",
      "河南市",
      "湖北省",
      "香港特别行政区",
      "澳门特别行政区"
    )
  }

  var province = provinces[0]
    set(value) {
      field = value
      invalidate()
    }

  init {
    paint.textSize = 50.dp
    paint.textAlign = Paint.Align.CENTER
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawText(province, width / 2f, height / 2f, paint)
  }
}