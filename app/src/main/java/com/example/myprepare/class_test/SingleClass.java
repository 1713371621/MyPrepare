package com.example.myprepare.class_test;

class SingleClass {

  private static volatile SingleClass instance = null;

  public static SingleClass getInstance() {
    if (instance == null) {
      synchronized (SingleClass.class) {
        if (instance == null) {
          instance = new SingleClass();
        }
      }
    }
    return instance;
  }

  private SingleClass() {
    if (instance == null) {
      throw new RuntimeException("?乆折");
    }
  }

}
