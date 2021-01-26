package com.example.myprepare.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R
import com.example.myprepare.generic_test.Apple
import com.example.myprepare.generic_test.Banana
import com.example.myprepare.generic_test.Fruit
import com.example.myprepare.generic_test.FruitShop
import java.lang.reflect.Field

class GenericTestActivity : AppCompatActivity() {

  companion object {

    private const val TAG = "GenericTestActivity"
  }

  val appleFruitShop: FruitShop<Apple> = FruitShop()
  val bananaFruitShop: FruitShop<Banana> = FruitShop()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_generic_test)


    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      Log.d(
        TAG,
        "onCreate: appleFruitShop typeName = ${appleFruitShop.javaClass.typeName}, " +
            "genericInterfaces = ${appleFruitShop.javaClass.genericInterfaces}, " +
            "genericSuperclass = ${appleFruitShop.javaClass.genericSuperclass}"
      )
      Log.d(
        TAG,
        "onCreate: bananaFruitShop typeName = ${bananaFruitShop.javaClass.typeName}, " +
            "genericInterfaces = ${bananaFruitShop.javaClass.genericInterfaces}, " +
            "genericSuperclass = ${bananaFruitShop.javaClass.genericSuperclass}"
      )
    }

    val appleFruitShopField: Field = this.javaClass.getDeclaredField("appleFruitShop")
    Log.d(TAG, "onCreate: appleFruitShopField.genericType = ${appleFruitShopField.genericType}")

    val bananaFruitShopField: Field = this.javaClass.getDeclaredField("bananaFruitShop")
    Log.d(TAG, "onCreate: bananaFruitShopField.genericType = ${bananaFruitShopField.genericType}")

    val appleFruitShop2: FruitShop<Apple> = object : FruitShop<Apple>() {}
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      Log.d(TAG, "onCreate: appleFruitShop2 is ${appleFruitShop2.javaClass.typeName}")
    }

    val fruitShop: FruitShop<out Fruit> = FruitShop<Apple>()
    val fruitShop1: FruitShop<in Apple> = FruitShop<Fruit>()
  }
}