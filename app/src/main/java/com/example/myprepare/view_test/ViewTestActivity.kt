package com.example.myprepare.view_test

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.TextView
import com.example.myprepare.R
import kotlin.concurrent.thread

class ViewTestActivity : Activity() {

  companion object {

    private const val TAG = "ViewTestActivity"
  }

  init {

  }

  val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
    override fun onGlobalLayout() {
      Log.d(TAG, "textView width: ${textView.width}")
      Log.d(TAG, "textView height: ${textView.height}")
    }

  }

  lateinit var textView: TextView
  
  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_view_test)
    
    textView = findViewById(R.id.view_test_text_view)
    
    textView.text = "??"

    thread {
      textView.text = "哈哈你崩给我看啊"
      textView.text = "哈哈你崩给我看啊1"
      textView.text = "哈哈你崩给我看啊2"
      textView.text = "哈哈你崩给我看啊3"
      textView.text = "哈哈你崩给我看啊4"
      textView.text = "哈哈你崩给我看啊5"
      textView.text = "哈哈你崩给我看啊6"
      textView.text = "哈哈你崩给我看啊7"
      textView.text = "哈哈你崩给我看啊8"
      textView.text = "哈哈你崩给我看啊${Thread.currentThread().name}"

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
        textView.text = "我搞起$str"
        i--
      }*/
    }

    textView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    Log.d(TAG, "onCreate: end")
  }

  override fun onDestroy() {
    textView.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
    super.onDestroy()
  }
}