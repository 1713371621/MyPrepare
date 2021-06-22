package com.example.myprepare.view_test.vote_button

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Keep
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.animation.PathInterpolatorCompat
import com.example.myprepare.databinding.VoteDanmakuContentBinding

@Keep
class VoteDanmakuView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  companion object {
    private const val TAG = "VoteDanmakuView"
    private const val DANMAKU_VOTE_DURATION = 6000
  }

  val binding: VoteDanmakuContentBinding = VoteDanmakuContentBinding.inflate(LayoutInflater.from(context), this)

  private val animatorSet: AnimatorSet
  private val alphaHindAnimator: ObjectAnimator

  private val animatorSetListener: Animator.AnimatorListener = object : Animator.AnimatorListener {
    override fun onAnimationStart(animation: Animator?) {
    }

    override fun onAnimationEnd(animation: Animator?) {
      showVoteDanmakuView(danmakuVote)
    }

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }
  }

  private val alphaHindAnimatorListener: Animator.AnimatorListener = object : Animator.AnimatorListener {
    override fun onAnimationStart(animation: Animator?) {
    }

    override fun onAnimationEnd(animation: Animator?) {
      visibility = View.GONE
    }

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }
  }

  init {
    val animatorInterpolator = PathInterpolatorCompat.create(0f, 0f, 0.2f, 1f)
    val alphaShowAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f).apply {
      interpolator = animatorInterpolator
    }
    val translateAnimator = ObjectAnimator.ofFloat(this, "translationY", 20f, 0f).apply {
      interpolator = animatorInterpolator
    }
    animatorSet = AnimatorSet().apply {
      playTogether(alphaShowAnimator, translateAnimator)
      duration = 300
    }
    alphaHindAnimator = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f).apply {
      interpolator = animatorInterpolator
      duration = 300
    }

    binding.option1.setOnClickListener {
      if (danmakuVote?.selected == -1 && !animatorSet.isRunning && !alphaHindAnimator.isRunning) {
        danmakuVote?.selected = 0
        danmakuVote?.option1Count = danmakuVote?.option1Count?.let {
          it + 1
        } ?: 1
        showVoteDanmakuView(danmakuVote, true)
      }
    }

    binding.option2.setOnClickListener {
      if (danmakuVote?.selected == -1 && !animatorSet.isRunning && !alphaHindAnimator.isRunning) {
        danmakuVote?.selected = 1
        danmakuVote?.option2Count = danmakuVote?.option2Count?.let {
          it + 1
        } ?: 1
        showVoteDanmakuView(danmakuVote, true)
      }
    }
  }

  private var danmakuVote: DanmakuVote? = null

  fun showVoteView(data: DanmakuVote?) {
    danmakuVote = data
    if (danmakuVote == null) {
      visibility = View.GONE
      return
    }

    // 如果用户已经选择过了, 则直接显示
    showVoteDanmakuView(danmakuVote)

    if (alphaHindAnimator.isRunning) {
      alphaHindAnimator.cancel()
    }
    alpha = 0f
    visibility = View.VISIBLE
    if (animatorSet.isRunning) {
      return
    }
    animatorSet.start()

  }

  fun hindVoteView() {
    if (animatorSet.isRunning) {
      animatorSet.cancel()
    }
    if (alphaHindAnimator.isRunning) {
      return
    }
    alphaHindAnimator.start()
  }

  @JvmOverloads
  fun showVoteDanmakuView(data: DanmakuVote?, showAnimation: Boolean = false) {
    danmakuVote = data
    danmakuVote?.let {
      binding.title.text = it.title
      val totalNum = it.option1Count + it.option2Count
      val option1Title = "A.${it.option1}"
      val option2Title = "B.${it.option2}"
      when (it.selected) {
        0 -> {
          binding.option1.showSelectedCount(option1Title, it.option1Count, totalNum, isUserSelected = true, showAnimation = showAnimation)
          binding.option2.showSelectedCount(option2Title, it.option2Count, totalNum, isUserSelected = false, showAnimation = showAnimation)
        }
        1 -> {
          binding.option1.showSelectedCount(option1Title, it.option1Count, totalNum, isUserSelected = false, showAnimation = showAnimation)
          binding.option2.showSelectedCount(option2Title, it.option2Count, totalNum, isUserSelected = true, showAnimation = showAnimation)
        }
        else -> {
          binding.option1.showUnselectedTitle(option1Title)
          binding.option2.showUnselectedTitle(option2Title)
        }
      }
    } ?: let {
      visibility = View.GONE
    }
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    Log.d(TAG, "onAttachedToWindow: ")
    alphaHindAnimator.removeAllListeners()
    alphaHindAnimator.addListener(alphaHindAnimatorListener)
    animatorSet.removeAllListeners()
    animatorSet.addListener(animatorSetListener)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    animatorSet.cancel()
    alphaHindAnimator.cancel()
  }

}