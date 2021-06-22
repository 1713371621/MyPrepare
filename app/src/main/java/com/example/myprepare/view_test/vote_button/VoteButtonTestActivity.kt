package com.example.myprepare.view_test.vote_button

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
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

    val tv1BgAnimator = ObjectAnimator.ofFloat(binding.voteButtonView, "alpha", 0f, 1f)
    val tv1TranslateY: ObjectAnimator = ObjectAnimator.ofFloat(binding.voteButtonView, "translationY", 20f, 0f)
//    val tv2TranslateY: ObjectAnimator = ObjectAnimator.ofFloat(binding.voteButtonView, "translationY", 0f, 300f, 0f)

    val animatorSet = AnimatorSet()
    animatorSet.playTogether(tv1BgAnimator, tv1TranslateY)
    animatorSet.duration = 2000


    binding.voteButtonView.showUnselectedTitle("A.想吃鸡腿")

    binding.button1.setOnClickListener {
      val danmakuVote = DanmakuVote(123, 123, "张三爱吃汉堡吗?", "爱吃", "不爱吃", 5, 5, -1)
      binding.voteDanmakuView.showVoteView(danmakuVote)
//      binding.voteButtonView.showUnselectedTitle("A.想吃鸡腿")
//      animatorSet.start()
    }
    binding.button1.setOnLongClickListener {
      binding.voteDanmakuView.hindVoteView()
      true
    }

    binding.button2.setOnClickListener {
      val danmakuVote = DanmakuVote(123, 123, "张三爱吃汉堡吗?", "爱吃", "不爱吃", 5, 5, 0)
      binding.voteDanmakuView.showVoteView(danmakuVote)
//      binding.voteButtonView.showSelectedCount("A.想吃鸡腿", 123, 500, isUserSelected = true, showAnimation = true)
    }
    binding.button3.setOnClickListener {
      val danmakuVote = DanmakuVote(123, 123, "张三爱吃汉堡吗?", "爱吃", "不爱吃", 5, 5, 1)
      binding.voteDanmakuView.showVoteView(danmakuVote)
//      binding.voteButtonView.showSelectedCount("A.想吃鸡腿", 222, 500, isUserSelected = false, showAnimation = true)
    }
    binding.button4.setOnClickListener {
//      binding.voteButtonView.showSelectedCount("A.想吃鸡腿", 333, 500, isUserSelected = true, showAnimation = false)
    }
  }
}