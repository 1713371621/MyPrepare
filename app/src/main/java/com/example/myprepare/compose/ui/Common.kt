package com.example.myprepare.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprepare.R
import com.example.myprepare.compose.ComposeViewModel
import com.example.myprepare.compose.ui.theme.MyPrepareTheme

@Composable
fun WeTopBar(title: String, onBack: (() -> Unit)? = null) {
  Box(
    Modifier
      .background(MyPrepareTheme.colors.background)
      .fillMaxWidth()
  ) {
//    Row(
//      Modifier.preferredHeight(48.dp)
//    ) {
//      if (onBack != null) {
//        Icon(
//          painterResource(R.drawable.ic_back),
//          null,
//          Modifier
//            .clickable(onClick = onBack)
//            .align(Alignment.CenterVertically)
//            .size(36.dp)
//            .padding(8.dp),
//          tint = MyPrepareTheme.colors.icon
//        )
//      }
//      Spacer(Modifier.weight(1f))
//      val viewModel: ComposeViewModel = viewModel()
//      Icon(
//        painterResource(R.drawable.ic_palette),
//        "切换主题",
//        Modifier
//          .clickable(
//            onClick = {
//              viewModel.theme = when (viewModel.theme) {
//                MyPrepareTheme.Theme.Light -> MyPrepareTheme.Theme.Dark
//                MyPrepareTheme.Theme.Dark -> MyPrepareTheme.Theme.Light
//              }
//            }
//          )
//          .align(Alignment.CenterVertically)
//          .size(36.dp)
//          .padding(8.dp),
//        tint = MyPrepareTheme.colors.icon
//      )
//    }
    Text(title, Modifier.align(Alignment.Center), color = MyPrepareTheme.colors.textPrimary)
  }
}