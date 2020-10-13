package com.example.myprepare

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast

object Utils {

  @JvmStatic
  fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics
    )
  }

  fun toast(string: String) {
    Toast.makeText(MyApplication.currentApplication(), "", Toast.LENGTH_SHORT).show()
  }
}