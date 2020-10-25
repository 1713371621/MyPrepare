package com.example.myprepare.class_test;

import com.example.myprepare.class_test.Test1Class.InnerClass;
import java.io.File;
import java.io.FileInputStream;

public class Test2Class {

  public static void main(String[] args) {
    Test1Class.Plants plants = new Test1Class().new Plants(1, " ", "");
    plants.display();

    Test1Class.InnerClass innerClass = new InnerClass();
    innerClass.say();

    Test1Class test1Class = new Test1Class();

    File file = new File("");
    try (
        FileInputStream fileInputStream = new FileInputStream(file);
    ) {

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
