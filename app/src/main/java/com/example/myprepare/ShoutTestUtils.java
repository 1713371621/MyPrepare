package com.example.myprepare;

import android.text.TextUtils;

class ShoutTestUtils {

  void shout(String name) {
    if (TextUtils.isEmpty(name)) {
      System.out.println("I am shout!!");
    } else {
      System.out.println("shout " + name);
    }
  }
}
