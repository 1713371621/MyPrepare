package com.example.myprepare.jet_pack

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myprepare.module.User

class UserProfileViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
  
  var userId: String = savedStateHandle["uid"] ?: ""
  
  var user: LiveData<User> = TODO()
}