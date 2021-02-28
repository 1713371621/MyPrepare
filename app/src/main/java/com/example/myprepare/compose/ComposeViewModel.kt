package com.example.myprepare.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myprepare.R
import com.example.myprepare.compose.data.Chat
import com.example.myprepare.compose.data.Msg
import com.example.myprepare.compose.data.User
import com.example.myprepare.compose.ui.theme.MyPrepareTheme

class ComposeViewModel : ViewModel() {
  var chats by mutableStateOf(
    listOf(
      Chat(
        friend = User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi),
        mutableStateListOf(
          Msg(User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi), "锄禾日当午"),
          Msg(User.Me, "汗滴禾下土"),
          Msg(User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi), "谁知盘中餐"),
          Msg(User.Me, "粒粒皆辛苦"),
          Msg(User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi), "唧唧复唧唧，木兰当户织。不闻机杼声，惟闻女叹息。"),
          Msg(User.Me, "双兔傍地走，安能辨我是雄雌？"),
          Msg(User("gaolaoshi", "高老师", R.drawable.avatar_gaolaoshi), "床前明月光，疑是地上霜。"),
          Msg(User.Me, "吃饭吧？").apply {
            read = true
          },
        )
      ),
      Chat(
        friend = User("diuwuxian", "丢物线", R.drawable.avatar_diuwuxian),
        mutableStateListOf(
          Msg(User("diuwuxian", "丢物线", R.drawable.avatar_diuwuxian), "哈哈哈"),
          Msg(User.Me, "你笑个屁呀"),
        )
      ),
    )
  )

  var selectedTab by mutableStateOf(0)

  var theme: MyPrepareTheme.Theme by mutableStateOf(MyPrepareTheme.Theme.Light)
}