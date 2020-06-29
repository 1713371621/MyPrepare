package com.example.lib_annotation_test;

import android.app.Activity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Binding {

  public static void bind(Activity activity) {
    // 通过反射对当前activity中添加注解的field进行绑定
    /*for (Field field : activity.getClass().getDeclaredFields()) {
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
    }*/

    // 通过启动对象的方式绑定view
    try {
      Class bindingClass = Class.forName(activity.getClass().getCanonicalName() + "Binding");
      Constructor constructor = bindingClass.getDeclaredConstructor(activity.getClass());
      constructor.newInstance(activity);
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
        InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }

  }
}
