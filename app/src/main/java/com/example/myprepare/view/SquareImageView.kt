package com.example.myprepare.view

import android.content.Context
import android.util.AttributeSet
import kotlin.math.min

class SquareImageView(context: Context, attrs: AttributeSet) :
  androidx.appcompat.widget.AppCompatImageView(context, attrs) {
  
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    
    val current = min(measuredHeight, measuredWidth)
    setMeasuredDimension(current, current)
  }
}