package com.example.myprepare.generic_test

interface AbstractShop<T> {
  
  fun setShop(t: T)
  
  fun getShop(): T?
  
  fun <E: Fruit> tradeInFruit(item: E, money: Float): E
}