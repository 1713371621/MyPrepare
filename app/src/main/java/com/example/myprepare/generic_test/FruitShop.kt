package com.example.myprepare.generic_test

open class FruitShop<T : Fruit> : AbstractShop<T>, Comparable<String> {
  
  private var t: T? = null
  
  override fun getShop(): T? {
    return t
  }
  
  override fun setShop(t: T) {
    this.t = t
    
  }
  
  override fun <E : Fruit> tradeInFruit(item: E, money: Float): E {
    return if ((money / 2).toInt() == 0) {
      Apple("$money") as E
    } else {
      Banana("$money") as E
    }
  }
  
  override fun compareTo(other: String): Int {
    return 0
  }
  
}

fun main() {
  val bananaShop = FruitShop<Banana>()
  bananaShop.setShop(Banana("香蕉人"))
}