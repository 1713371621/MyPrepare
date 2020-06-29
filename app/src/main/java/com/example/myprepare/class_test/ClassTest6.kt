package com.example.myprepare.class_test

fun main() {

}

class Outer: Person {
  inner class Inner {
    val outer = this@Outer
  }
}

interface Person {

}