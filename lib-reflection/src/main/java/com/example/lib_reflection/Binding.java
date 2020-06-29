package com.example.lib_reflection;

import android.app.Activity;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Binding {

  public static void bind(Activity activity) {
    // 通过反射对当前activity中添加注解的field进行绑定
    for (Field field : activity.getClass().getDeclaredFields()) {
      BindView bindView = field.getAnnotation(BindView.class);
      if (bindView != null) {
        field.setAccessible(true);
        try {
          field.set(activity, activity.findViewById(bindView.value()));
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
      field.setAccessible(true);
    }
  }
}
