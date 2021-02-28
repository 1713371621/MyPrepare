package com.example.myprepare.compose.ui.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import com.example.myprepare.compose.data.Chat
import com.example.myprepare.compose.ui.WeTopBar
import com.example.myprepare.compose.ui.chatListItem
import com.example.myprepare.compose.ui.theme.MyPrepareTheme

@Composable
fun ChatList(chats: List<Chat>) {
  Column(
    modifier = Modifier
      .background(MyPrepareTheme.colors.background)
      .fillMaxSize()
  ) {
    WeTopBar(title = "Octopus")
    // ViewOverlay 做小红点
    LazyColumn(
      modifier = Modifier.background(MyPrepareTheme.colors.listItem)
    ) {
      itemsIndexed(chats) { index, chat ->
        chatListItem(chat = chat)
        if (index < chats.size - 1) {
          Divider(
            startIndent = 68.dp,
            color = MyPrepareTheme.colors.divider,
            thickness = 0.8f.dp
          )
        }
      }
    }
  }
}

fun Modifier.unread(show: Boolean, badgeColor: Color): Modifier = this.drawWithContent {
  drawContent()
  if (show) {
    drawIntoCanvas { canvas ->
      val paint = Paint().apply {
        color = badgeColor
      }
      canvas.drawCircle(
        Offset(size.width - 1.dp.toPx(), 1.dp.toPx()),
        5.dp.toPx(),
        paint
      )
    }
  }
}