package com.example.myprepare.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableEmitter
import io.reactivex.rxjava3.core.FlowableOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

class RxJava3Activity : AppCompatActivity() {

    companion object {
        private const val TAG = "RxJava3Activity"
    }

    private var subscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava3)

//         拆一下
//        val single: Single<Int> = Single.just(1)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//        val singleString = single.map(object : Function<Int, String> {
//            override fun apply(t: Int?): String {
//                return t.toString()
//            }
//        })
//        singleString.subscribe(object : SingleObserver<String> {
//            override fun onSubscribe(d: Disposable?) {
//
//            }
//
//            override fun onSuccess(t: String?) {
//                rxjava3text.text = t
//            }
//
//            override fun onError(e: Throwable?) {
//            }
//        })
//
//        Observable.interval(0, 1, TimeUnit.SECONDS)
//            .subscribeOn(Schedulers.single())
//            .observeOn(AndroidSchedulers.mainThread())
//            .delay(1, TimeUnit.SECONDS)
//            .subscribe(object : Observer<Long?> {
//                override fun onSubscribe(d: Disposable?) {
//
//                }
//
//                override fun onNext(t: Long?) {
//                    rxjava3text.text = t.toString()
//                }
//
//                override fun onError(e: Throwable?) {
//                }
//
//                override fun onComplete() {
//                }
//
//            })

        Flowable.create(object : FlowableOnSubscribe<Int> {
            override fun subscribe(emitter: FlowableEmitter<Int>?) {
                Log.d(TAG, "subscribe: first requested = ${emitter?.requested()}")
                var flag: Boolean
                for (i: Int in 0 until 30) {
                    flag = false
                    while (emitter?.requested() ?: -1 == 0L) {
                        if (!flag) {
                            Log.d(TAG, "subscribe: can not emit value")
                            flag = true
                        }
                    }
                    emitter?.onNext(i)
                    Log.d(TAG, "subscribe: emit $i, requested = ${emitter?.requested()}")
                }
                emitter?.onComplete()
            }

        }, BackpressureStrategy.ERROR)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(2000, TimeUnit.MILLISECONDS)
            .subscribe(object : Subscriber<Int> {
                override fun onSubscribe(s: Subscription?) {
                    Log.d(TAG, "onSubscribe: ")
                    subscription = s
                    subscription?.request(2)
                }

                override fun onNext(t: Int?) {
                    Log.d(TAG, "onNext: $t")
                    subscription?.request(1)
                    subscription?.request(1)
                }

                override fun onError(t: Throwable?) {
                    t?.printStackTrace()
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                    subscription?.cancel()
                }

            })
        Log.d(TAG, "onCreate: end!")
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription?.cancel()
    }
}