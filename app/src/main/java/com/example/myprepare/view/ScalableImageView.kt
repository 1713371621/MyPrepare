package com.example.myprepare.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.example.myprepare.R
import com.example.myprepare.dp
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class ScalableImageView(context: Context, attrs: AttributeSet) : View(context, attrs) {
  
  companion object {
    
    private const val TAG = "ScalableImageView"
    private const val EXTRA_SCALE_FACTOR = 3f
  }
  
  private val IMAGE_SIZE: Int = 250.dp.toInt()
  
  private val bitmap: Bitmap = getAvatar(resources, IMAGE_SIZE)
  private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
  
  private var offsetX: Float = 0f
  private var offsetY: Float = 0f
  private var originalOffsetX: Float = 0f
  private var originalOffsetY: Float = 0f
  
  private val overScroller: OverScroller = OverScroller(context)
  
  private var smallScale: Float = 1f
  private var bigScale: Float = 1f
  
  private var currentScale: Float = 1f
    set(value) {
      field = value
      invalidate()
    }
  
  private val myGestureDetectorListener = MyGestureDetectorListener()
  private val myFlingRunnable = MyFlingRunnable()
  private val myScaleGestureDetectorListener = MyScaleGestureDetectorListener()
  
  private val scaleAnimator: ValueAnimator =
    ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)
  
  private val gestureDetectorCompat: GestureDetectorCompat =
    GestureDetectorCompat(context, myGestureDetectorListener)
  private val scaleGestureDetector: ScaleGestureDetector =
    ScaleGestureDetector(context, myScaleGestureDetectorListener)

  init {
  
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 20.dp
  }
  
  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    
    originalOffsetX = (width - IMAGE_SIZE) / 2f
    originalOffsetY = (height - IMAGE_SIZE) / 2f
    
    if ((bitmap.width / bitmap.height.toFloat()) > (width / height.toFloat())) {
      smallScale = width / bitmap.width.toFloat()
      bigScale = (height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR).toFloat()
    } else {
      smallScale = height / bitmap.height.toFloat()
      bigScale = (width / bitmap.width.toFloat() * EXTRA_SCALE_FACTOR).toFloat()
    }
    
    currentScale = smallScale
    scaleAnimator.setFloatValues(smallScale, bigScale)
  }
  
  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    
    val scaleFraction: Float = (currentScale - smallScale) / (bigScale - smallScale)
    canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
    canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
    canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
  }
  
  override fun onTouchEvent(event: MotionEvent): Boolean {
    scaleGestureDetector.onTouchEvent(event)
    if (!scaleGestureDetector.isInProgress) {
      gestureDetectorCompat.onTouchEvent(event)
    }
    return true
  }
  
  private fun getAvatar(res: Resources, width: Int): Bitmap {
    val id: Int = R.drawable.batman
    val options: BitmapFactory.Options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(res, id, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(res, id, options)
  }
  
  private fun fixOffset() {
    val absoluteDistanceX = abs((bitmap.width * bigScale - width) / 2f)
    offsetX = min(offsetX, absoluteDistanceX)
    offsetX = max(offsetX, -absoluteDistanceX)
    
    val absoluteDistanceY = abs((bitmap.height * bigScale - height) / 2f)
    offsetY = min(offsetY, absoluteDistanceY)
    offsetY = max(offsetY, -absoluteDistanceY)
  }
  
  inner class MyGestureDetectorListener : GestureDetector.SimpleOnGestureListener() {
    
    // GesturedDetector.OnGestureListener
    override fun onDown(e: MotionEvent?): Boolean {
      Log.d(TAG, "onDown: ")
      return true
    }
    
    override fun onScroll(
      e1: MotionEvent?,
      e2: MotionEvent?,
      distanceX: Float,
      distanceY: Float
    ): Boolean {
      Log.d(TAG, "onScroll: distanceX = $distanceX, distanceY = $distanceY")

//      if (big) {
      offsetX -= distanceX
      offsetY -= distanceY
      fixOffset()
      invalidate()
//      }
      return false
    }
    
    override fun onFling(
      e1: MotionEvent?,
      e2: MotionEvent?,
      velocityX: Float,
      velocityY: Float
    ): Boolean {
      Log.d(TAG, "onFling: velocityX = $velocityX, velocityY = $velocityY")

//      if (big) {
      val scale = bigScale
      
      val absoluteDistanceX = abs((bitmap.width * scale - width) / 2f)
      val absoluteDistanceY = abs((bitmap.height * scale - height) / 2f)
      
      overScroller.fling(
        offsetX.toInt(),
        offsetY.toInt(),
        velocityX.toInt(),
        velocityY.toInt(),
        (-absoluteDistanceX).toInt(),
        absoluteDistanceX.toInt(),
        (-absoluteDistanceY).toInt(),
        absoluteDistanceY.toInt(),
        40.dp.toInt(),
        40.dp.toInt()
      )
      ViewCompat.postOnAnimation(this@ScalableImageView, myFlingRunnable)
//      }
      return false
    }
    
    // doubleTapListener
    
    override fun onDoubleTap(e: MotionEvent): Boolean {
      Log.d(TAG, "onDoubleTap: offsetX = $offsetX, offsetY = $offsetY")
      if (currentScale < smallScale + (bigScale - smallScale) * 0.4f) {
        offsetX = -(e.x - width / 2f) * (bigScale / currentScale)
        offsetY = -(e.y - height / 2f) * (bigScale / currentScale)
        fixOffset()
        scaleAnimator.setFloatValues(currentScale, bigScale)
        scaleAnimator.start()
      } else {
        scaleAnimator.setFloatValues(bigScale, smallScale)
        scaleAnimator.start()
      }
      Log.d(TAG, "onDoubleTap: bottom offsetX = $offsetX, offsetY = $offsetY")
      return true
    }
  }
  
  inner class MyScaleGestureDetectorListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
    
    override fun onScale(detector: ScaleGestureDetector): Boolean {
      Log.d(TAG, "onScale: detector.scaleFactor = ${detector.scaleFactor}")
      val tempCurrentScale = currentScale * detector.scaleFactor
      if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
        return false
      } else {
        currentScale = tempCurrentScale.coerceAtLeast(smallScale).coerceAtMost(bigScale)
        return true
      }
    }
    
    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
      Log.d(TAG, "onScaleBegin: offsetX = $offsetX, offsetY = $offsetY")
      offsetX += (width / 2f - detector.focusX) * detector.scaleFactor
      offsetY += (height / 2f - detector.focusY) * detector.scaleFactor
//      fixOffset()
      return true
    }
    
    override fun onScaleEnd(detector: ScaleGestureDetector) {
      Log.d(TAG, "onScaleEnd: offsetX = $offsetX, offsetY = $offsetY")
    }
  }
  
  inner class MyFlingRunnable : Runnable {
    
    // Runnable
    override fun run() {
      if (overScroller.computeScrollOffset()) {
        offsetX = overScroller.currX.toFloat()
        offsetY = overScroller.currY.toFloat()
        invalidate()
        ViewCompat.postOnAnimation(this@ScalableImageView, this)
      }
    }
  }
}