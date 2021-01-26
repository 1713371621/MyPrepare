package com.example.myprepare.jet_pack.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myprepare.jet_pack.Webservice
import com.example.myprepare.jet_pack.bean.User
import com.example.myprepare.jet_pack.database.dao.UserDao
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val webservice: Webservice,
  private val userDao: UserDao
) {

  private val scope = MainScope()

  fun getUser(userId: String): LiveData<User> {
    refreshUser(userId)
    return userDao.load(userId)
  }

  private fun refreshUser(userId: String) {
    scope.launch {
      // Check if user data was fetched recently.
//      val userExists: Boolean = userDao.hasUser(FRESH_TIMEOUT)
      val userExists = true
      if (!userExists) {
        try {
          val response = webservice.getUser(userId)
          userDao.save(response)
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }
    }
  }
}