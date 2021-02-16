package com.example.myprepare.compose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myprepare.compose.ui.theme.MyPrepareTheme

class ComposeMainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)
    setContent {
      MyPrepareTheme {
        ComposeTest()
      }
    }
  }

  @Preview
  @Composable
  fun ComposeTest() {
    Surface(Modifier) {
      Text("fuck you android studio 4.2 and compose")
    }
  }

}