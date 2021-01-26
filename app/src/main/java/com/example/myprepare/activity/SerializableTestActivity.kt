package com.example.myprepare.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.class_test.SerializableTestData
import java.io.*


class SerializableTestActivity : AppCompatActivity() {

  companion object {

    private const val TAG = "SerializableTestActivit"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_serializable_test)

    val serializableTestData = SerializableTestData("xiaoming", 12)
    //序列化过程

    //序列化过程
    val file = File("sdcard/test.txt")
    val fileOutputStream = FileOutputStream(file)
    val objectOutputStream = ObjectOutputStream(fileOutputStream)
    objectOutputStream.writeObject(serializableTestData)
    objectOutputStream.close()
    fileOutputStream.close()
    println("序列化成功！")
    //反序列化过程
    //反序列化过程
    val fileInputStream = FileInputStream(file)
    val objectInputStream = ObjectInputStream(fileInputStream)
    val newSerializableTestData: SerializableTestData =
      objectInputStream.readObject() as SerializableTestData
    objectInputStream.close()
    fileInputStream.close()
    println("反序列化成功！")
    Log.d(TAG, "age:" + newSerializableTestData.name + "  age:" + newSerializableTestData.age)
  }
}