package com.example.myprepare.module

import android.util.Log

class User {
  companion object {
    
    private const val TAG = "User"
  }
  
  var userName: String? = null
    get() {
      Log.d(TAG, ": ")
      return field
    }
    set(value) {
      Log.d(TAG, "value: $value")
      field = value
    }
  
  @JvmField
  var phoneNumber: String? = null
}