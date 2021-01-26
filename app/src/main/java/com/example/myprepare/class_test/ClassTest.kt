package com.example.myprepare.class_test

class ClassTest {

  companion object {

    var phone: String = ""
  }

  var age: Int? = null
  var userName: String? = null

  init {
    val plant = Plant(1, "2", "3")
    plant.colour
  }

  inner class Plant(a: Int, n: String, c: String) {

    var colour: String? = null

    init {
      age = a
      userName = n
      colour = c
    }
  }

  // internal修饰的类, 只能在包内访问
  internal class StaticInner {

    init {
    }

    companion object {

      val p = phone
    }
  }

}