package com.example.myprepare.class_test;

import android.util.Log;

public class Test1Class {

  public int age;
  public String name;

  private static final String TAG = "Test1Class";


  public void say() {
    Log.d(TAG, "say: 你吼");
  }

  public class Plants {

    public String colour;

    public Plants(int a, String n, String c) {
      age = a;
      name = n;
      colour = c;
    }

    public void display() {
      Log.d(TAG, "display: name = " + name + ", color = " + colour);
    }
  }

  public static class InnerClass {

    private String string = "早上好";

    public void say() {
      Log.d(TAG, "say: " + string);
    }
  }

  public class InnerTest1Class {

  }

  public static class StaticTest1Class {

  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
  }
}
