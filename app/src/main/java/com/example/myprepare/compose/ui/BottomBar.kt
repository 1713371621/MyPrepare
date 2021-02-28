package com.example.myprepare.compose.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprepare.R
import com.example.myprepare.compose.ComposeViewModel
import com.example.myprepare.compose.ui.theme.*

@Composable
fun BottomBar(pager: Int, currentChanged: (Int) -> Unit) {
  val viewModel: ComposeViewModel = viewModel()
  Row(
    modifier = Modifier.background(MyPrepareTheme.colors.onBackground)
  ) {
    TabItem(
      if (pager == 0) R.drawable.vip_ic_bot_tab_home_selected else R.drawable.vip_ic_bot_tab_home,
      "章鱼",
      modifier = Modifier
        .clickable {
          viewModel.selectedTab = 0
          currentChanged(0)
        }
        .weight(1f),
      color = if (pager == 0) yellow1 else gray1
    )

    TabItem(
      if (pager == 1) R.drawable.vip_ic_bot_tab_discovery_selected else R.drawable.vip_ic_bot_tab_discovery,
      "亮点内容",
      modifier = Modifier
        .clickable {
          viewModel.selectedTab = 1
          currentChanged(1)
        }
        .weight(1f), color = if (pager == 1) blue2 else gray1
    )

    TabItem(
      if (pager == 2) R.drawable.vip_ic_bot_tab_channel_selected else R.drawable.vip_ic_bot_tab_channel,
      "球",
      modifier = Modifier
        .clickable {
          viewModel.selectedTab = 2
          currentChanged(2)
        }
        .weight(1f), color = if (pager == 2) blue2 else gray1
    )

    TabItem(
      if (pager == 3) R.drawable.vip_ic_bot_tab_topic_selected else R.drawable.vip_ic_bot_tab_topic,
      "聊天室",
      modifier = Modifier
        .clickable {
          viewModel.selectedTab = 3
          currentChanged(3)
        }
        .weight(1f), color = if (pager == 3) blue2 else gray1
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
  MyPrepareTheme(theme = MyPrepareTheme.Theme.Dark) {
    BottomBar(0) {}
  }
}

@Preview(showBackground = true)
@Composable
fun ContentBodyNight() {
  MyPrepareTheme(theme = MyPrepareTheme.Theme.Light) {
    BottomBar(1) {}
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
