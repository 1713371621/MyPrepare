package com.example.myprepare.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_edit_text.*

class EditTextActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_text)
    initView()
  }
  
  private fun initView() {
    edit_text_text_2.imeOptions = EditorInfo.IME_ACTION_NEXT
    edit_text_text_1.setOnEditorActionListener(object : TextView.OnEditorActionListener {
      override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
//        Logger.d("actionId = $actionId, event = $event")
        return true
      }
  
    })
  }
}