package com.example.myprepare.activity

import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.example.myprepare.R

class BitmapDrawableActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_bitmap_drawable)
    
    /*val bitmap: Bitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
    bitmap.toDrawable(resources)
    
    val drawable:Drawable = ColorDrawable()
    drawable.toBitmap()*/
  }
}