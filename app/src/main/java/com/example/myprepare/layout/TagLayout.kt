package com.example.myprepare.layout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TagLayout(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
  
  private val childrenBounds: MutableList<Rect> = mutableListOf()
  
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    
    val widthMeasureSpecMode = MeasureSpec.getMode(widthMeasureSpec)
    val widthMeasureSpecSize = MeasureSpec.getSize(widthMeasureSpec)
    
    var widthUsed = 0
    var heightUsed = 0
    var lineWidthUsed = 0
    var lineMaxHeight = 0
    // 遍历子view, 调用measure()
    for ((index, child) in children.withIndex()) {
      // 子view的width类型
      /*val childLayoutParams: LayoutParams = child.layoutParams
        var childWidthMeasureSpecMode = 0
        var childWidthMeasureSpecSize = 0
      
        var childHeightMeasureSpecMode = 0
        var childHeightMeasureSpecSize = 0
        when (childLayoutParams.width) {
        LayoutParams.MATCH_PARENT -> {
          when (widthMeasureSpecMode) {
            MeasureSpec.UNSPECIFIED -> {
              childWidthMeasureSpecMode = MeasureSpec.UNSPECIFIED
              childWidthMeasureSpecSize = 0
            }
            MeasureSpec.EXACTLY, MeasureSpec.AT_MOST -> {
              childWidthMeasureSpecMode = MeasureSpec.EXACTLY
              childWidthMeasureSpecSize = widthMeasureSpecSize - childUseWidth
            }
          }
        }
        LayoutParams.WRAP_CONTENT -> {
          when (widthMeasureSpecMode) {
            MeasureSpec.UNSPECIFIED -> {
              childWidthMeasureSpecMode = MeasureSpec.UNSPECIFIED
              childWidthMeasureSpecSize = 0
            }
            MeasureSpec.EXACTLY, MeasureSpec.AT_MOST -> {
              childWidthMeasureSpecMode = MeasureSpec.AT_MOST
              childWidthMeasureSpecSize = widthMeasureSpecSize - childUseWidth
            }
          }
        }
        else -> {
          childWidthMeasureSpecMode = MeasureSpec.EXACTLY
          childWidthMeasureSpecSize = childLayoutParams.width
        }
      }
      // 将结果保存
      child.measure(childWithMeasure, childHeightMeasure)*/
      
      // 计算自己的selfWidth和selfHeight
      measureChildWithMargins(
        child,
        widthMeasureSpec,
        0,
        heightMeasureSpec,
        heightUsed
      )
      
      if (lineWidthUsed + child.measuredWidth > widthMeasureSpecSize && widthMeasureSpecMode != MeasureSpec.UNSPECIFIED) {
        lineWidthUsed = 0
        heightUsed += lineMaxHeight
        lineMaxHeight = 0
        // 再次测量
        measureChildWithMargins(
          child,
          widthMeasureSpec,
          0,
          heightMeasureSpec,
          heightUsed
        )
      }
      if (index >= childrenBounds.size) {
        childrenBounds.add(Rect())
      }
      
      val childBounds = childrenBounds[index]
      
      // 设置子view的width和height
      childBounds.set(
        lineWidthUsed,
        heightUsed,
        lineWidthUsed + child.measuredWidth,
        heightUsed + child.measuredHeight
      )
      
      lineWidthUsed += child.measuredWidth
      widthUsed = max(widthUsed, lineWidthUsed)
      lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
    }
    
    setMeasuredDimension(widthUsed, heightUsed)
  }
  
  override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
    return MarginLayoutParams(context, attrs)
  }
  
  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    for ((index, child) in children.withIndex()) {
      val childBounds = childrenBounds[index]
      child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom)
    }
  }
}