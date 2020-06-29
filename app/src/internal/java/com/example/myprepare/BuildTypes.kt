package com.example.myprepare

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.myprepare.drawable.MeshDrawable
import com.example.testlibrary.randomColor

fun Activity.drawBadge() {
  val badgeView = View(this)
  badgeView.background =
    MeshDrawable(listOf("#1abc9c", "#2ecc71", "#3498db", "#9b59b6", "#34495e").randomColor())
  val decorView: FrameLayout = window.decorView as FrameLayout
  decorView.addView(
    badgeView,
    ViewGroup.LayoutParams.MATCH_PARENT,
    ViewGroup.LayoutParams.MATCH_PARENT
  )
}