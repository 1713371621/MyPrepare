package com.example.myprepare.view_test

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import com.example.myprepare.R
import kotlinx.android.synthetic.main.activity_view_test.*
import kotlin.concurrent.thread

class ViewTestActivity : Activity() {
  
  companion object {
    
    private const val TAG = "ViewTestActivity"
  }
  
  init {
  
  }
  
  val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
    override fun onGlobalLayout() {
      Log.d(TAG, "view_test_text_view width: ${view_test_text_view.width}")
      Log.d(TAG, "view_test_text_view height: ${view_test_text_view.height}")
    }
    
  }
  
  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_view_test)
    view_test_text_view.text = "??"
    
    thread {
      view_test_text_view.text = "哈哈你崩给我看啊"
      view_test_text_view.text = "哈哈你崩给我看啊1"
      view_test_text_view.text = "哈哈你崩给我看啊2"
      view_test_text_view.text = "哈哈你崩给我看啊3"
      view_test_text_view.text = "哈哈你崩给我看啊4"
      view_test_text_view.text = "哈哈你崩给我看啊5"
      view_test_text_view.text = "哈哈你崩给我看啊6"
      view_test_text_view.text = "哈哈你崩给我看啊7"
      view_test_text_view.text = "哈哈你崩给我看啊8"
      view_test_text_view.text = "哈哈你崩给我看啊${Thread.currentThread().name}"
      
      /*Looper.prepare()
      val button = Button(this)
      windowManager.addView(button, WindowManager.LayoutParams())
      button.setOnClickListener {
        button.textSize = 20.dp
        button.text = """
          ${Thread.currentThread().name}: ${SystemClock.uptimeMillis()}
        """.trimIndent()
      }
      Looper.loop()*/
      
      /*SystemClock.sleep(3000)
      var i = 10
      while (i > 0) {
  
        SystemClock.sleep(1000)
        val str = i.toString()
        view_test_text_view.text = "我搞起$str"
        i--
      }*/
    }
    
    view_test_text_view.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    Log.d(TAG, "onCreate: end")
  }
  
  override fun onDestroy() {
    view_test_text_view.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
    super.onDestroy()
  }
}