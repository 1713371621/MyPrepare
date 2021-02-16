package com.example.myprepare.activity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.service.MyIntentService
import com.example.myprepare.service.MyService

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

    val startIntentServiceButton: Button = findViewById(R.id.start_intent_service)
    val startServiceButton: Button = findViewById(R.id.start_service)
    val stopServiceButton: Button = findViewById(R.id.stop_service)
    val bindServiceButton: Button = findViewById(R.id.bind_service)
    val unBindServiceButton: Button = findViewById(R.id.unbind_service)

    startIntentServiceButton.setOnClickListener {
      startService(intentServiceIntent)
    }

    val intentService = Intent(this, MyService::class.java)
    startServiceButton.setOnClickListener {
      startService(intentService)
    }

    stopServiceButton.setOnClickListener {
      stopService(intentService)
    }

    bindServiceButton.setOnClickListener {
      bindService(intentService, serviceConnection, BIND_AUTO_CREATE)
    }

    unBindServiceButton.setOnClickListener {
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