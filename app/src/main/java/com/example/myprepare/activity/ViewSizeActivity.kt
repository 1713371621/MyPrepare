package com.example.myprepare.activity

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_view_size.*

class ViewSizeActivity : AppCompatActivity() {

    companion object{
        const val t1:String = "<p>Tell me the <b><font color=\"blue\">color</font></b>.</p>"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_size)


        view_size_text.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(t1, FROM_HTML_MODE_LEGACY)
        } else{
            Html.fromHtml(t1)
        }


        view_size_text.post {
            Logger.d("text view width is ${view_size_text.width}, height is ${view_size_text.height}")
        }

        val viewTreeObserver: ViewTreeObserver = view_size_button.viewTreeObserver
        viewTreeObserver.addOnDrawListener(object : ViewTreeObserver.OnGlobalLayoutListener,
            ViewTreeObserver.OnDrawListener {
            override fun onGlobalLayout() {
                Logger.d("button width is ${view_size_button.width}, height is ${view_size_button.height}")
                view_size_button.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }

            override fun onDraw() {
                Logger.d("onDraw")
            }
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Logger.d("image width is ${view_size_image_view.width}, height is ${view_size_image_view.height}")
    }
}