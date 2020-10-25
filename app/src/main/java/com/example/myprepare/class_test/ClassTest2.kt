package com.example.myprepare.class_test

class TestClass0 : TestClass2() {
  // TODO: 2020/10/15 by lazy
  // TODO: 2020/10/15 let also run apply区别
}

class TestClass1 : TestClass2() {
  
}

abstract class TestClass2 {
  
  val testClass3: TestClass3 = TestClass3()
  
  //  interface OnClickListener {
//
//    fun onClick()
//  }
//
//  fun setOnClickListener(onClickListener: OnClickListener) {
//
//  }
  fun setOnClickListener(listener: (String) -> Int) {
    
  }
}

class TestClass3 {
  
}

fun onClick(string: String): Int {
  return string.length
}

fun main() {
//  val testClass2 = TestClass2()
//  testClass2.setOnClickListener(object : TestClass2.OnClickListener {
//    override fun onClick() {
//
//    }
//
//  })
  
  // 接口还是老老实实的用匿名内部类吧...
//  val testClass2 = TestClass2()
//  testClass2.setOnClickListener(::onClick)
  
  println(TestClass0().testClass3.hashCode())
  println(TestClass1().testClass3.hashCode())
}