package com.example.myprepare

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast

object Utils {

  @JvmStatic
  fun Float.dp2px(): Float {
    return TypedValue.applyDimension(
      TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )
  }

  // java代码使用重载, 需要添加如下注解
  @JvmOverloads
  fun toast(string: String, showToast: Int = Toast.LENGTH_LONG) {
    Toast.makeText(MyApplication.currentApplication, "", showToast).show()
  }
}