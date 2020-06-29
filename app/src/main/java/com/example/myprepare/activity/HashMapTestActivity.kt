package com.example.myprepare.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R

class HashMapTestActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hash_map_test)
    
    val hashMap: MutableMap<String, String> = HashMap()
    hashMap["fuck"] = "you"
  }
}