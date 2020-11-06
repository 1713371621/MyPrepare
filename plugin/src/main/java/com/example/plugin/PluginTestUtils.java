package com.example.plugin;

import java.util.Random;

public class PluginTestUtils {

  public void printARandomNum(String name) {
    System.out.println(name + ", number = " + new Random().nextInt(100));
  }
}
