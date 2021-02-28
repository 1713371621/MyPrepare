package com.example.myprepare.compose.data

import androidx.annotation.DrawableRes
import com.example.myprepare.R

data class User(val id: String, val name: String, @DrawableRes val avatar: Int) {
  companion object {
    val Me: User = User(id = "rengwuxian", name = "扔物线-朱凯", R.drawable.avatar_rengwuxian)
  }
}
