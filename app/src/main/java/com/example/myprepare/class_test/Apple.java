package com.example.myprepare.class_test;

public class Apple {

  String color;

  public Apple(String color) {
    this.color = color;
  }

  String getColor() {
    return color;
  }

  void setColor(String color) {
    this.color = color;
  }

  public String toString() {
    return new StringBuilder("Apple[color=").append(this.color).append("]").toString();
  }

  // 当对象被GC回收时，会回调finalize()方法
  protected void finalize() throws Throwable {
    System.out.println(this);
  }
}
