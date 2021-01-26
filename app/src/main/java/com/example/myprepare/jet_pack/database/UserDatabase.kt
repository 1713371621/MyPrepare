package com.example.myprepare.jet_pack.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myprepare.jet_pack.bean.User
import com.example.myprepare.jet_pack.database.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
  abstract fun userDao(): UserDao
}