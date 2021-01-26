package com.example.myprepare.class_test

import java.lang.ref.WeakReference

class ClassTest4 {
  init {
    var student: Student? = Student()
    student?.name = "wo"
  }

  fun changeStudent(student: Student?) {
    student?.name = null
  }
}

class WeakStudent(referent: Student?) : WeakReference<Student>(referent) {

}

class Student {

  var name: String? = null
}

class WeakString(referent: StringBuffer?) : WeakReference<StringBuffer>(referent) {

}

fun main() {
  val weak = WeakString(StringBuffer("student"))

  System.gc()
  try {
    Thread.sleep(5000)
  } catch (e: InterruptedException) {
    e.printStackTrace()
  }

  println(weak.get() == null) // true
}