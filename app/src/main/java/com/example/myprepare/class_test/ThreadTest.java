package com.example.myprepare.class_test;

public class ThreadTest {

  public static void main(String[] args) {
    ThreadTest threadTest = new ThreadTest();
    threadTest.printNumber();
  }

  public void printNumber() {
    for (int i = 0; i < 52; i++) {
      System.out.println(i + 1);
    }
  }
}
