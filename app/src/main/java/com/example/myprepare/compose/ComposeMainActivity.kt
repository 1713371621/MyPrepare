package com.example.myprepare.compose

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprepare.compose.ui.BottomBar
import com.example.myprepare.compose.ui.Pager
import com.example.myprepare.compose.ui.PagerState
import com.example.myprepare.compose.ui.pager.ChatList
import com.example.myprepare.compose.ui.theme.MyPrepareTheme

class ComposeMainActivity : AppCompatActivity() {

  @SuppressLint("MissingSuperCall")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val viewModel: ComposeViewModel = viewModel()
      MyPrepareTheme(viewModel.theme) {
        Surface {
          Column(Modifier.background(MyPrepareTheme.colors.background)) {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            val posterWidthDp = screenWidth * 0.6f
            val pagerState: PagerState = run {
              val clock = AmbientAnimationClock.current
              remember(clock) {
                PagerState(clock, maxPage = 3)
              }
            }
            Box {
              Column {
                Button(
                  onClick = {
                    val intent = Intent()
                    startActivity(intent)
                  },
                  modifier = Modifier.size(30.dp, 15.dp)
                ) {
                  Box {
                    Text(text = "点我")
                  }
                }
                Pager(state = pagerState, Modifier.weight(1f)) {
                  when (page) {
                    0 -> ChatList(viewModel.chats)
                    1 -> Box(Modifier.fillMaxSize())
                    2 -> Box(Modifier.fillMaxSize())
                    3 -> Box(Modifier.fillMaxSize())
                  }
                }
                BottomBar(viewModel.selectedTab) {
                  viewModel.selectedTab = it
                }
              }
            }
          }
        }
      }
    }
  }
}