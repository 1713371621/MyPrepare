package com.example.myprepare.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.myprepare.R
import com.orhanobut.logger.Logger
import java.util.concurrent.*

class ThreadTestActivity : AppCompatActivity() {

    private val monitor = java.lang.Object()
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_test)

        threadTest()
    }

    private fun threadTest() {
        val synchronousQueue: SynchronousQueue<Runnable> = SynchronousQueue<Runnable>()
        val executor: ExecutorService =
            ThreadPoolExecutor(5, 100, 10, TimeUnit.SECONDS, synchronousQueue)

        val thread1 = Thread() {
            SystemClock.sleep(1000)
            printName("1")
        }
        thread1.start()

        val thread2 = Thread() {
            SystemClock.sleep(2000)
            initName()
        }
        thread2.start()

        val thread3 = Thread() {
            printName("3")
        }
        thread3.start()

        Logger.d("")
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