package com.example.myprepare.class_test

class TestClass0 : TestClass2() {

  fun test() {
    val test1 = TestClass1()
  }

  protected class ProtectedTest {

    protected fun test() {
      val protectedTest1 = ProtectedTest1()
    }

    fun test1() {
      test()
      val protectedTest1 = ProtectedTest1()
    }
  }

  protected class ProtectedTest1 {

    protected fun test() {
      val protectedTest1 = ProtectedTest1()
    }
  }
}

internal class TestClass1 : TestClass2() {

  fun test() {
  }
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

  fun lambdaTest(
    firstName: String,
    lastName: String,
    age: Int,
    fuckYou: (String, String) -> Int,
    whoIsYourMother: (Int) -> String
  ) {
    println("your mother is: ${whoIsYourMother(age)}, fuckYou: ${fuckYou(firstName, lastName)}")
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

//  println(TestClass0().testClass3.hashCode())
//  println(TestClass1().testClass3.hashCode())

  val testClass1 = TestClass1()
  testClass1.lambdaTest("sssssss", "BBBBBSBSBSB", 133,
    fuckYou = { s: String, s2: String -> s.length + s2.length },
    whoIsYourMother = { i: Int ->
      var name = "NT"
      for (index: Int in 0 until i) {
        name += "死"
      }
      return@lambdaTest name
    })
}