package com.example.myprepare.jet_pack.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.myprepare.jet_pack.bean.User

@Dao
interface UserDao {
  @Insert(onConflict = REPLACE)
  fun save(user: User)

  @Query("SELECT * FROM user WHERE id = :userId")
  fun load(userId: String): LiveData<User>

}