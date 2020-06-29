package com.example.myprepare.generic_test;

import java.util.ArrayList;
import java.util.List;

class GenericTest {

  public static void main(String[] args) {

    FruitShop<Apple> appleFruitShop = new FruitShop<Apple>(){};

    FruitShop<Fruit> fruitShop = new FruitShop<>();
    fruitShop.setShop(new Apple("apple"));
    FruitShop<? super Apple> appleShop = fruitShop;
    Fruit apple = appleShop.getShop();
    Apple apple1 = appleShop.tradeInFruit(new Apple("香蕉"), 100f);

    List<String> a = new ArrayList<String>(){};
  }

  <E extends Sim> E takeIn(E items) {
    return (E) new ChinaMobile("");
  }
}

