package com.example.myprepare.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TouchEventTestView(context: Context, attrs: AttributeSet) : View(context, attrs) {

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    return super.onTouchEvent(event)
  }
}