package com.example.myprepare.class_test

class SequenceTest() {
  init {
    val sequence = sequenceOf(1, 3, 4, 2, 6, 5, 8, 6, 7)
    val result: String = sequence.map {
      println("map = $it")
      "${it}_呵呵"
    }.first{
      println("first = $it")
      true
    }
    println("result = $result")
  
    val list = listOf(1, 3, 4, 2, 6, 5, 8, 6, 7)
    val listResult: String = list.map {
      println("map = $it")
      "${it}_呵呵"
    }.first{
      println("first = $it")
      true
    }
    println("listResult = $listResult")
    
  }
}

fun main() {
  SequenceTest()
}