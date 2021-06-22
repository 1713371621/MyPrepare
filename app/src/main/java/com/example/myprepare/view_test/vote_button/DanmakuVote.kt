package com.example.myprepare.view_test.vote_button

import com.google.gson.annotations.SerializedName

/**
 * @param option1 选项一的标题
 * @param option2 选项二的标题
 * @param selected 用户选项, -1: 用户未选择, 0: 选则opeion1, 1: 选择option2
 */
data class DanmakuVote(
  @SerializedName("id")
  val id: Long,
  @SerializedName(value = "snaptime")
  val pos: Int = 0,
  @SerializedName(value = "title")
  val title: String = "",
  @SerializedName(value = "option_1")
  var option1: String? = "",
  @SerializedName(value = "option_2")
  var option2: String? = "",
  @SerializedName(value = "option_1_count")
  var option1Count: Int = 0,
  @SerializedName(value = "option_2_count")
  var option2Count: Int = 0,
  @SerializedName(value = "selected")
  var selected: Int = -1
)
