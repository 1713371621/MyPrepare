package com.example.myprepare.activity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.service.MyIntentService
import com.example.myprepare.service.MyService
import kotlinx.android.synthetic.main.activity_service_test.*

class ServiceTestActivity : AppCompatActivity() {
  companion object {
    
    private const val TAG = "ServiceTestActivity"
  }
  
  @Volatile
  private var isBindService = false
  
  private val serviceConnection: BindServiceConnection = BindServiceConnection()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_service_test)
    val intentServiceIntent = Intent(this, MyIntentService::class.java)
    
    start_intent_service.setOnClickListener {
      startService(intentServiceIntent)
    }
    
    val intentService = Intent(this, MyService::class.java)
    start_service.setOnClickListener {
      startService(intentService)
    }
    
    stop_service.setOnClickListener {
      stopService(intentService)
    }
    
    bind_service.setOnClickListener {
      bindService(intentService, serviceConnection, BIND_AUTO_CREATE)
    }
    
    unbind_service.setOnClickListener {
      if (!isBindService) {
        unbindService(serviceConnection)
      }
    }
  }
  
  inner class BindServiceConnection : ServiceConnection {
    
    override fun onServiceConnected(name: ComponentName?, service: IBinder) {
      Log.d(TAG, "onServiceConnected: ")
      val myServiceBinder = service as MyService.MyBinder
      val myService = myServiceBinder.getService()
      isBindService = true
    }
    
    override fun onServiceDisconnected(name: ComponentName?) {
      Log.d(TAG, "onServiceDisconnected: ")
      isBindService = false
    }
    
  }
}