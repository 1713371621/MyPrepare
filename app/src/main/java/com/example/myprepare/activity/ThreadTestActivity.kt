package com.example.myprepare.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.orhanobut.logger.Logger
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock

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

    threadTest()
  }

  private fun threadTest() {
    val synchronousQueue: SynchronousQueue<Runnable> = SynchronousQueue<Runnable>()
    val executor: ExecutorService =
        ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, synchronousQueue)

//        val thread1 = Thread() {
//            SystemClock.sleep(1000)
//            printName("1")
//        }
//        thread1.start()
//
//        val thread2 = Thread() {
//            SystemClock.sleep(2000)
//            initName()
//        }
//        thread2.start()
//
//        val thread3 = Thread() {
//            printName("3")
//        }
//        thread3.start()
//
//        Logger.d("")

    Thread {
      executor.execute {
        while (i.get() != 1_000_000) {
          if (Thread.interrupted()) {
            i.set(0)
            return@execute
          }
          i.incrementAndGet()
          if (i.get() == 9999) {
            println("threadTest: i = ${i.get()}")
          }
        }

        Log.d(
            TAG,
            "threadTest: isTerminated: ${executor.isTerminated}, isShutDown: ${executor.isShutdown}"
        )
      }
//            SystemClock.sleep(100)
//            executor.shutdownNow()
    }.start()

  }

  private fun printName(num: String) {
    synchronized(monitor) {
      Logger.d("printName-$num: start")
      while (name.isBlank()) {
        Logger.d("printName-$num: start while")
        monitor.wait()
        Logger.d("printName-$num: end while")
      }
      Logger.d("printName-$num: print my name: $name")
    }
  }

  private fun initName() {
    synchronized(monitor) {
      name = "RESTFulS"
      monitor.notifyAll()
      Logger.d("notifyAll")
    }
  }

}