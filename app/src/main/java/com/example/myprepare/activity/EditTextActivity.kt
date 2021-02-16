package com.example.myprepare.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R

class EditTextActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_text)
    initView()
  }

  private fun initView() {
    val editText1: EditText = findViewById(R.id.edit_text_text_1)
    val editText2: EditText = findViewById(R.id.edit_text_text_2)
    editText2.imeOptions = EditorInfo.IME_ACTION_NEXT
    editText1.setOnEditorActionListener(object : TextView.OnEditorActionListener {
      override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
//        Logger.d("actionId = $actionId, event = $event")
        return true
      }

    })
  }
}