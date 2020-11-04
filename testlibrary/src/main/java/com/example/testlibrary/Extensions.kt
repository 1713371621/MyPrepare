package com.example.testlibrary

import android.util.Log
import androidx.core.graphics.toColorInt
import java.util.*

private const val TAG = "Extensions"

fun List<String>.randomColor(): Int {
  return if (this.isNullOrEmpty()) {
     "#ffffff".toColorInt()
  } else{
    val randomIndex = Random().nextInt(this.size)
    Log.d(TAG, "randomColor: size = ${this.size}, randomIndex = $randomIndex")
    this[randomIndex].toColorInt()
  }
}