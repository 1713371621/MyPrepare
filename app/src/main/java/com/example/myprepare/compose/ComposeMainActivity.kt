package com.example.myprepare.compose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myprepare.R
import com.example.myprepare.compose.ui.theme.*

class ComposeMainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)
    setContent {
      MyPrepareTheme(darkTheme = true) {
        Column {
          BottomBar("章鱼")
        }
      }
    }
  }

  @Composable
  fun BottomBar(selected: String) {
    Row(
      modifier = Modifier.background(MyPrepareTheme.colors.onBackground)
    ) {
      TabItem(
        if (selected == "章鱼") R.drawable.vip_ic_bot_tab_home_selected else R.drawable.vip_ic_bot_tab_home,
        "章鱼",
        modifier = Modifier.weight(1f),
        color = if (selected == "章鱼") yellow1 else black2
      )

      TabItem(
        if (selected == "亮点内容") R.drawable.vip_ic_bot_tab_discovery_selected else R.drawable.vip_ic_bot_tab_discovery,
        "亮点内容",
        modifier = Modifier.weight(1f),
        color = if (selected == "亮点内容") blue2 else black2
      )

      TabItem(
        if (selected == "球") R.drawable.vip_ic_bot_tab_channel_selected else R.drawable.vip_ic_bot_tab_channel,
        "球",
        modifier = Modifier.weight(1f),
        color = if (selected == "球") blue2 else black2
      )

      TabItem(
        if (selected == "聊天室") R.drawable.vip_ic_bot_tab_topic_selected else R.drawable.vip_ic_bot_tab_topic,
        "聊天室",
        modifier = Modifier.weight(1f),
        color = if (selected == "聊天室") blue2 else black2
      )
    }
  }

  @Composable
  fun TabItem(
    @DrawableRes iconId: Int,
    title: String,
    modifier: Modifier = Modifier,
    color: Color
  ) {
    Column(
      modifier = modifier.padding(vertical = 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Icon(
        painter = painterResource(id = iconId),
        contentDescription = title,
        modifier = Modifier.size(24.dp),
        tint = color
      )
      Text(
        text = title,
        fontSize = 11.sp,
        color = color
      )
    }
  }

  @Preview(showBackground = true)
  @Composable
  fun ContentBody() {
    MyPrepareTheme(darkTheme = false) {
      BottomBar("章鱼")
    }
  }

  @Preview(showBackground = true)
  @Composable
  fun ContentBodyNight() {
    MyPrepareTheme(darkTheme = true) {
      BottomBar("亮点内容")
    }
  }

  @Preview
  @Composable
  fun BottomIcon() {
    val selected = "章鱼"
    Row {
      TabItem(
        if (selected == "章鱼") R.drawable.vip_ic_bot_tab_home_selected else R.drawable.vip_ic_bot_tab_home,
        "章鱼",
        modifier = Modifier.weight(1f),
        color = if (selected == "章鱼") yellow1 else white2
      )
    }
  }

}