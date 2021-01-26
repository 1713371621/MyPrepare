package com.example.myprepare.jet_pack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.R

class JetPackTestActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_jet_pack_test)

    val fragmentManager = supportFragmentManager
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentManager.findFragmentByTag(UserProfileFragment::class.java.simpleName) ?: let {
      fragmentTransaction.add(
        R.id.jet_pack_fragment,
        UserProfileFragment(),
        UserProfileFragment::class.java.simpleName
      )
    }
    fragmentTransaction.commit()
  }
}