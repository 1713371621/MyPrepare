package com.example.myprepare

import android.app.Application
import android.content.Context
import android.os.Process
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileReader

class MyApplication : Application() {
  
  companion object {
    
    // 注解均为java程序使用
    @JvmStatic
    @get:JvmName("currentApplication")
    lateinit var currentApplication: Context
      private set
    
    val progressName: String
      get() {
        var fileInputStream: FileInputStream? = null
        var bufferedReader: BufferedReader? = null
        var fileReader: FileReader? = null
        try {
          val file = File("/proc/${Process.myPid()}/cmdline")
          fileInputStream = FileInputStream(file)
          fileReader = FileReader(file)
          bufferedReader = BufferedReader(fileReader)
//          bufferedReader = fileInputStream.bufferedReader()
          return bufferedReader.readLine().trim()
        } finally {
          fileInputStream?.close()
          bufferedReader?.close()
          fileReader?.close()
        }
      }
  }
  
  
  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    currentApplication = this
  }
  
  override fun onCreate() {
    super.onCreate()
    initLogger()
    val pid: Int = Process.myPid()
    Logger.d("pid = $pid, progressName = $progressName")
  }
  
  private fun initLogger() {
    val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
      .showThreadInfo(false)      //（可选）是否显示线程信息。 默认值为true
      .methodCount(2)             // （可选）要显示的方法行数。 默认2
      .methodOffset(0)            // （可选）设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
      .tag("MyPrepare")           //（可选）每个日志的全局标记。 默认PRETTY_LOGGER（如上图）
      .build()
    
    Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
      override fun isLoggable(priority: Int, tag: String?): Boolean {
        return BuildConfig.DEBUG
      }
    })
  }
}