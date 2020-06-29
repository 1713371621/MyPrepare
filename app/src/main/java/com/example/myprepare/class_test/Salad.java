package com.example.myprepare.class_test;


import java.lang.ref.WeakReference;

public class Salad extends WeakReference<Apple> {
  Apple apple;

  public Salad(Apple referent) {
    super(referent);
  }

}


