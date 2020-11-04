package com.example.myprepare.class_test

class ClassTest3 {
  
  fun haha(a: Int, b: Int, lamb: () -> Unit, lambTest: (c: Int, d: Int) -> Int) {
    println("start, a = $a, b = $b")
    lamb.invoke()
    println("result: ${lambTest(a, b)}")
    println("end")
  }
}

fun main() {
  val classTest3 = ClassTest3()
  classTest3.haha(
    1, 5, {
      println("准备！！")
    }, { c: Int, d: Int ->
      return@haha (c + 1) * (d + 1)
    }
  )
}