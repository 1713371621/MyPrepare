package com.example.myprepare.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
  
  companion object {
    
    private const val TAG = "MyService"
  }
  
  inner class MyBinder : Binder() {
    
    fun getService(): MyService {
      return this@MyService
    }
  }
  
  private val myBinder: MyBinder = MyBinder()
  
  override fun onCreate() {
    super.onCreate()
    Log.d(TAG, "onCreate: ")
  }
  
  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    Log.d(TAG, "onStartCommand: ")
    return super.onStartCommand(intent, flags, startId)
  }
  
  override fun onDestroy() {
    Log.d(TAG, "onDestroy: ")
    super.onDestroy()
  }
  
  override fun onBind(intent: Intent): IBinder {
    Log.d(TAG, "onBind: ")
    return myBinder
  }
  
  override fun onUnbind(intent: Intent?): Boolean {
    Log.d(TAG, "onUnbind: ")
    
    return super.onUnbind(intent)
  }
}