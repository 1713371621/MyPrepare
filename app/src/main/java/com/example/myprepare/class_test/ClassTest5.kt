package com.example.myprepare.class_test

import java.lang.ref.WeakReference

class ClassTest5 {
}

fun main() {
  // saladWithRedApple 引用的Apple对象符合弱引用回收规则
  val saladWithRedApple = Salad(Apple("red "))

  val green = Apple("green")
  // saladWithGreenApple 引用的Apple对象不符合弱引用回收规则，因为它同时被green这个强引用所引用
  val saladWithGreenApple = Salad(green)

  val weakReference = WeakReference(Apple("yellow"))
  System.gc()
  try {
    Thread.sleep(5000)
  } catch (e: InterruptedException) {
    e.printStackTrace()
  }
  println(saladWithRedApple.get() == null) // true

  println(saladWithGreenApple.get() == null) // false

  println(weakReference.get() == null) // false

}