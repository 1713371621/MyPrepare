package com.example.myprepare.jet_pack

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myprepare.jet_pack.bean.User
import com.example.myprepare.jet_pack.database.UserRepository

class UserProfileViewModel @ViewModelInject constructor(
  @Assisted savedStateHandle: SavedStateHandle,
  userRepository: UserRepository
) : ViewModel() {

  var userId: String = savedStateHandle["uid"] ?: throw IllegalArgumentException("missing user id")

  var user: LiveData<User> = userRepository.getUser(userId)
}