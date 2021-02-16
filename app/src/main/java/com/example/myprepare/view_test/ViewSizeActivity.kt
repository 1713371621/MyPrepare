package com.example.myprepare.view_test

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R

class ViewSizeActivity : AppCompatActivity() {

  companion object {

    const val t1: String = "<p>Tell me the <b><font color=\"blue\">color</font></b>.</p>"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_view_size)

    val sizeText: TextView = findViewById(R.id.view_size_text)

    sizeText.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      Html.fromHtml(t1, FROM_HTML_MODE_LEGACY)
    } else {
      Html.fromHtml(t1)
    }


    sizeText.post {
//      Logger.d("text view width is ${view_size_text.width}, height is ${view_size_text.height}")
    }

    val sizeButton: Button = findViewById(R.id.view_size_button)

    val viewTreeObserver: ViewTreeObserver = sizeButton.viewTreeObserver
    viewTreeObserver.addOnDrawListener(object : ViewTreeObserver.OnGlobalLayoutListener,
      ViewTreeObserver.OnDrawListener {
      override fun onGlobalLayout() {
//        Logger.d("button width is ${view_size_button.width}, height is ${view_size_button.height}")
        sizeButton.viewTreeObserver.removeOnGlobalLayoutListener(this)
      }

      override fun onDraw() {
//        Logger.d("onDraw")
      }
    })
  }

  override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
//    Logger.d("image width is ${view_size_image_view.width}, height is ${view_size_image_view.height}")
  }
}