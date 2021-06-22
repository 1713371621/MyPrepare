package com.example.myprepare.view_test.vote_button

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.myprepare.databinding.ActivityVoteButtonTestBinding

class VoteButtonTestActivity : AppCompatActivity() {
  private lateinit var binding: ActivityVoteButtonTestBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityVoteButtonTestBinding.inflate(LayoutInflater.from(this))
    setContentView(binding.root)
    binding.voteButtonView.showUnselectedTitle("A.想吃鸡腿")

    binding.button1.setOnClickListener {
      binding.voteButtonView.showUnselectedTitle("A.想吃鸡腿")
    }

    binding.button2.setOnClickListener {
      binding.voteButtonView.showSelectedCount("A.想吃鸡腿", 123, 500, isUserSelected = true, showAnimation = true)
    }
    binding.button3.setOnClickListener {
      binding.voteButtonView.showSelectedCount("A.想吃鸡腿", 222, 500, isUserSelected = false, showAnimation = true)
    }
    binding.button4.setOnClickListener {
      binding.voteButtonView.showSelectedCount("A.想吃鸡腿", 333, 500, isUserSelected = true, showAnimation = false)
    }
  }
}