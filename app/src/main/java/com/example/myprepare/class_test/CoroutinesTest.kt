package com.example.myprepare.class_test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutinesTest {

  fun startTest1() {
    println("time = ${System.currentTimeMillis()}")
    repeat(100_000) {
      GlobalScope.launch {
        delay(10)
        print(".")
      }
    }

//    while (true) {
//    }
  }
}

fun main() {
  CoroutinesTest().startTest1()
}