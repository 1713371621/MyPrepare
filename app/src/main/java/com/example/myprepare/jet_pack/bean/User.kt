package com.example.myprepare.jet_pack.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
  @PrimaryKey val id: String,
  val name: String,
  val lastName: String
) {
}