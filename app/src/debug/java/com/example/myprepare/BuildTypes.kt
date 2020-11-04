package com.example.myprepare

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.myprepare.drawable.MeshDrawable

fun Activity.drawBadge() {
  val badgeView = View(this)
  badgeView.background = MeshDrawable(Color.parseColor("#fe5352"))
  val decorView: FrameLayout = window.decorView as FrameLayout
  decorView.addView(
    badgeView,
    ViewGroup.LayoutParams.MATCH_PARENT,
    ViewGroup.LayoutParams.MATCH_PARENT
  )
}