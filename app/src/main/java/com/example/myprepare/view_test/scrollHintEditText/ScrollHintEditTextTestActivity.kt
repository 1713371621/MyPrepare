package com.example.myprepare.view_test.scrollHintEditText

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.databinding.ActivityScrollHintEditTextTestBinding

class ScrollHintEditTextTestActivity : AppCompatActivity() {
  companion object {
    private const val TAG = "ScrollHintEditTextTestActivity"
  }

  lateinit var binding: ActivityScrollHintEditTextTestBinding

  private val textWatcher: TextWatcher = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      Log.d(TAG, "beforeTextChanged: ")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
      Log.d(TAG, "onTextChanged: ")
    }

    override fun afterTextChanged(s: Editable?) {
      Log.d(TAG, "afterTextChanged: ")
    }

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityScrollHintEditTextTestBinding.inflate(LayoutInflater.from(this))
    setContentView(binding.root)
    binding.scrollHintEditText.addTextChangedListener(textWatcher)
  }

  override fun onDestroy() {
    binding.scrollHintEditText.removeTextChangedListener(textWatcher)
    super.onDestroy()
  }
}