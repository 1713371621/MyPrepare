package com.example.myprepare.plugin_and_hot_fix;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myprepare.R;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PluginAndHotFixActivity extends AppCompatActivity {

  private static final String TAG = "PluginAndHotFixActivity";

  private TextView pluginAndHotFixTitle;

  private Button loadPluginButton;
  private Button showTitleButton;
  private Button hotfixButton;
  private Button removeHotfixButton;
  private Button killButton;

  private PluginAndHotFixOnClickListener onClickListener = new PluginAndHotFixOnClickListener();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_plugin_and_hot_fix);

    initView();
  }

  private void initView() {
    pluginAndHotFixTitle = findViewById(R.id.pluginAndHotFixTitle);

    loadPluginButton = findViewById(R.id.loadPlugin);
    loadPluginButton.setOnClickListener(onClickListener);

    showTitleButton = findViewById(R.id.showTitle);
    showTitleButton.setOnClickListener(onClickListener);

    hotfixButton = findViewById(R.id.hotfix);
    hotfixButton.setOnClickListener(onClickListener);

    removeHotfixButton = findViewById(R.id.removeHotfix);
    removeHotfixButton.setOnClickListener(onClickListener);

    killButton = findViewById(R.id.kill);
    killButton.setOnClickListener(onClickListener);
  }

  private class PluginAndHotFixOnClickListener implements OnClickListener {

    @Override
    public void onClick(View v) {
      // 不建议用switch/case了....google 搞事情
      int id = v.getId();
      if (id == R.id.loadPlugin) {
        clickLoadPluginButton();
      } else if (id == R.id.showTitle) {
        clickShowTitleButton();
      } else if (id == R.id.hotfix) {
        clickHotfixButton();
      } else if (id == R.id.removeHotfix) {

      } else if (id == R.id.kill) {

      }
    }

    private void clickKillButton() {

    }

    private void clickRemoveHotfix() {

    }

    private void clickHotfixButton() {
      File file = new File(getCacheDir() + "/hotfix.dex");

      if (!file.exists()) {
        try (
            InputStream inputStream = getAssets().open("apk/hotfix.dex");
            OutputStream outputStream = new FileOutputStream(file);
        ) {
          int read;
          byte[] buffer = new byte[4096];
          while ((read = inputStream.read(buffer)) != -1) {
            Log.d(TAG, "onCreate: read = " + read);
            outputStream.write(buffer, 0, read);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      ClassLoader originalClassLoader = getClassLoader();
      DexClassLoader dexClassLoader = new DexClassLoader(file.getPath(), getCacheDir().getPath(),
          null, null);
      try {
        // 获取到自己传入dex的classloader中的pathlist
        Class loaderClass = BaseDexClassLoader.class;
        Field pathListField = loaderClass.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathListObject = pathListField.get(dexClassLoader);

        Class pathListClass = pathListObject.getClass();
        Field dexElementsField = pathListClass.getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        Object dexElementsObject = dexElementsField.get(pathListObject);

        // 获取原本的classloader中的pathlist
        /*Class originalLoaderClass = originalClassLoader.getClass();
        Field originalPathListField = originalLoaderClass.getDeclaredField("pathList");
        originalPathListField.setAccessible(true);

        Object originalPathListObject = originalPathListField.get(originalLoaderClass);
        Field originalDexElementsField = originalPathListObject.getClass()
            .getDeclaredField("dexElements");
        originalDexElementsField.setAccessible(true);

        originalDexElementsField.set(originalPathListObject, dexElementsObject);*/

        Object originalPathListObject = pathListField.get(originalClassLoader);
        Object originalDexElementsObject = dexElementsField.get(originalPathListObject);

//        dexElementsField.set(originalPathListObject, dexElementsObject);

        // 将数组合并
        int oldLength = Array.getLength(originalDexElementsObject);
        int newLength = Array.getLength(dexElementsObject);
        Object concatDexElementsObject = Array
            .newInstance(dexElementsObject.getClass().getComponentType(), oldLength + newLength);

        for (int i = 0; i < newLength; i++) {
          Array.set(concatDexElementsObject, i, Array.get(dexElementsObject, i));
        }

        for (int i = 0; i < oldLength; i++) {
          Array.set(concatDexElementsObject, newLength + i, Array.get(concatDexElementsObject, i));
        }
        dexElementsField.set(originalPathListObject, concatDexElementsObject);

//        originalClassLoader.pathList.dexElements = dexClassLoader.pathList.dexElementes;
      } catch (IllegalAccessException | NoSuchFieldException e) {
        e.printStackTrace();
      }
    }

    private void clickShowTitleButton() {
      Title title = new Title();
      pluginAndHotFixTitle.setText(title.getTitle());
    }

    private void clickLoadPluginButton() {
    /*Utils utils = new Utils();
    utils.shout();*/
      try {
//    Class utilsClass = ShoutTestUtils.class;
//      Class shoutTestUtilsClass = Class.forName("com.example.myprepare.ShoutTestUtils");

        File file = new File(getCacheDir() + "/plugin.apk");

        if (!file.exists()) {
          try (
              InputStream inputStream = getAssets().open("apk/plugin.apk");
              OutputStream outputStream = new FileOutputStream(file);
          ) {
            int read;
            byte[] buffer = new byte[4096];
            while ((read = inputStream.read(buffer)) != -1) {
              Log.d(TAG, "onCreate: read = " + read);
              outputStream.write(buffer, 0, read);
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }

        ClassLoader classLoader = new DexClassLoader(file.getPath(), getCacheDir().getPath(), null,
            null);

        Class shoutPluginTestUtilsClass = classLoader
            .loadClass("com.example.plugin.PluginTestUtils");
        Constructor shoutPluginTestUtilsConstructor = shoutPluginTestUtilsClass
            .getDeclaredConstructors()[0];
        shoutPluginTestUtilsConstructor.setAccessible(true);
//      Object utils = utilsClass.newInstance();
        Object shoutTestUtils = shoutPluginTestUtilsConstructor.newInstance();
        Method shoutMethod = shoutPluginTestUtilsClass
            .getDeclaredMethod("printARandomNum", String.class);
        shoutMethod.setAccessible(true);
        shoutMethod.invoke(shoutTestUtils, "栈帧!!");
      } catch (IllegalAccessException | InstantiationException | NoSuchMethodException |
          InvocationTargetException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
}