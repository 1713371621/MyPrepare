package com.example.myprepare.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myprepare.compose.data.Chat
import com.example.myprepare.compose.ui.pager.unread
import com.example.myprepare.compose.ui.theme.MyPrepareTheme

@Composable
fun chatListItem(chat: Chat) {
  Row(
    Modifier.fillMaxWidth()
  ) {
    Image(
      painterResource(chat.friend.avatar),
      contentDescription = chat.friend.name,
      Modifier
        .padding(8.dp)
        .size(48.dp)
        .unread(!chat.msgs.last().read, badgeColor = MyPrepareTheme.colors.badge)
        .clip(RoundedCornerShape(4.dp))
    )
    Column(
      modifier = Modifier
        .weight(1f)
        .align(Alignment.CenterVertically)
    ) {
      Text(chat.friend.name, fontSize = 17.sp, color = MyPrepareTheme.colors.textPrimary)
      Text(chat.msgs.last().text, fontSize = 14.sp, color = MyPrepareTheme.colors.textSecondary)
    }
    Text(
      "13:48",
      Modifier.padding(8.dp, 8.dp, 12.dp, 8.dp),
      fontSize = 11.sp, color = MyPrepareTheme.colors.textSecondary
    )
  }
}