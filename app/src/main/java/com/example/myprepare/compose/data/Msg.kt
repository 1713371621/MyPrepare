package com.example.myprepare.compose.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Msg(val from: User, val text: String) {
  var read: Boolean by mutableStateOf(false)
}