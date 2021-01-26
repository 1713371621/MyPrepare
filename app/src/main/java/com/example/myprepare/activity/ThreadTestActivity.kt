package com.example.myprepare.activity

import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import java.util.*
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.thread

class ThreadTestActivity : AppCompatActivity() {

  private val monitor = java.lang.Object()
  private var name = ""
  private var i: AtomicInteger = AtomicInteger(0)

  private val reentrantReadWriteLock: ReentrantReadWriteLock = ReentrantReadWriteLock()
  private val readLock = reentrantReadWriteLock.readLock()
  private val writeLock = reentrantReadWriteLock.writeLock()

  private val lock: ReentrantLock = ReentrantLock()
  private final val condition: Condition = lock.newCondition()
  private val queue: Queue<String> = LinkedList()

  companion object {

    private const val TAG = "ThreadTestActivity"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_thread_test)

//    threadTest()
//    printTestMessage()
    test5()
  }

  fun test5() {
    val handlerThread = HandlerThread("test")
    handlerThread.start()
    val handler = object : Handler(handlerThread.looper) {
      override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        Log.d(TAG, "handleMessage: ")
      }
    }
    handler.post {
      SystemClock.sleep(1000)
      Log.d(TAG, "test5: ")
    }

  }

  fun test4() {
    thread {
      while (true) {
        SystemClock.sleep(1000)
        Log.d(TAG, "test4: ")
      }
    }
  }

  fun test3() {
    val runnable = object : Runnable {
      override fun run() {

      }

    }
    val handler = Handler()
    handler.post(runnable)
  }

  fun test2() {
    val cacheExecutor = Executors.newCachedThreadPool()
  }

  internal class MyAsyncTask() : AsyncTask<Int, Int, Int>() {

    override fun onPreExecute() {
      println("onPreExecute: top")
      super.onPreExecute()
      //运行在UI线程
      //在执行后台耗时操作之前,通常用于一些初始化操作,比如进度条显示
      println("onPreExecute: bottom")
    }

    override fun doInBackground(vararg params: Int?): Int {
      Log.d(TAG, "doInBackground: ${Thread.currentThread().name}")
      params.forEach {
        println("doInBackground: top: params = $it")
        SystemClock.sleep(1000)
        publishProgress(it)
      }
      return -1
    }

    override fun onPostExecute(result: Int?) {
      Log.d(TAG, "onPostExecute: ${Thread.currentThread().name}")
      println("onPostExecute: top: result = ${result.toString()}")
      super.onPostExecute(result)
      println("onPostExecute: bottom")
    }

    override fun onProgressUpdate(vararg values: Int?) {
      //运行在UI线程
      //在doBackground方法中,每次调用publishProgress方法都会触发该方法
      println("onProgressUpdate: top")
      values.forEach {
        println("onProgressUpdate: top: params = $it")
      }
      super.onProgressUpdate(*values)
      println("onProgressUpdate: bottom")
    }

  }

  fun test1() {
    val asyncTask = MyAsyncTask()
    asyncTask.execute(1, 2, 3, 4, 5, 6, 7, 8)
  }

  private var isNum: Boolean = true
  private var index: Int = 1

  private fun printTestMessage() {
    Log.d(TAG, "printTestMessage: ")
    Thread {
      for (i: Int in 0 until 26) {
        printNumber()
      }
    }.start()

    Thread {
      for (i: Int in 0 until 26) {
        printLetter()
      }
    }.start()
    Log.d(TAG, "printTestMessage: end")
  }

  private fun printNumber() {
    synchronized(monitor) {
      if (!isNum) {
        monitor.wait()
      }
      Log.d(TAG, "printNumber: ${index * 2 - 1}")
      Log.d(TAG, "printNumber: ${index * 2}")
      isNum = false
      monitor.notify()
    }
  }

  private fun printLetter() {
    synchronized(monitor) {
      if (isNum) {
        monitor.wait()
      }
      Log.d(TAG, "printLetter: ${('A' + index - 1)}")
      index++
      isNum = true
      monitor.notify()
    }
  }

  private fun threadTest() {
    val synchronousQueue: SynchronousQueue<Runnable> = SynchronousQueue<Runnable>()
    val executor: ExecutorService =
      ThreadPoolExecutor(1, 6, 10, TimeUnit.SECONDS, synchronousQueue)

    val thread2 = Thread() {
      SystemClock.sleep(2000)
      initName()
    }

    val thread1 = Thread() {
      SystemClock.sleep(1000)
//      thread2.join()
      printName("1")
    }
    val thread3 = Thread() {
      SystemClock.sleep(500)
      printName("3")
    }

    val thread4 = Thread() {
      SystemClock.sleep(500)
      printName("4")
    }

    executor.execute {
      SystemClock.sleep(100)
      printName("5")
    }

    executor.execute {
      SystemClock.sleep(200)
      printName("6")
    }

    executor.execute {
      SystemClock.sleep(300)
      printName("7")
    }

    executor.execute {
      SystemClock.sleep(300)
      printName("8")
    }

    executor.execute {
      SystemClock.sleep(300)
      printName("9")
    }

    executor.execute {
      SystemClock.sleep(300)
      printName("10")
    }

    thread1.start()
    thread2.start()
    thread3.start()
    thread4.start()

//
//        val thread3 = Thread() {
//            printName("3")
//        }
//        thread3.start()
//
//        Log.d(TAG, "")

//    Thread {
//      executor.execute {
//        while (i.get() != 1_000_000) {
//          if (Thread.interrupted()) {
//            i.set(0)
//            return@execute
//          }
//          i.incrementAndGet()
//          if (i.get() == 9999) {
//            println("threadTest: i = ${i.get()}")
//          }
//        }
//
//        Log.d(
//            TAG,
//            "threadTest: isTerminated: ${executor.isTerminated}, isShutDown: ${executor.isShutdown}"
//        )
//      }
//      SystemClock.sleep(100)
//      executor.shutdownNow()
//    }.start()

//    Thread {
//      lock.lock()
//      try {
//        var i = 100
//        while (i > 0) {
//          Thread {
//            printMessage(Thread.currentThread().id.toString())
//          }.start()
//          i--
//        }
//
//      } finally {
//        lock.unlock()
//      }
//    }.start()
  }

  private fun printMessage(message: String) {
    val lock: ReentrantLock = this.lock
    lock.lock()
    try {
      for (i: Int in 0..50) {
        Log.d(TAG, "printMessage: $message: $i")
      }
    } finally {
      lock.unlock()
    }
  }

  private fun printName(num: String) {
    synchronized(monitor) {
      Log.d(TAG, "printName-$num: start")
      while (name.isBlank()) {
        Log.d(TAG, "printName-$num: start while")
        monitor.wait()
        Log.d(TAG, "printName-$num: end while")
      }
      Log.d(TAG, "printName-$num: print my name: $name")
    }
  }

  private fun initName() {
    synchronized(monitor) {
      name = "RESTFulS"
      monitor.notifyAll()
      Log.d(TAG, "notifyAll")
    }
  }

}