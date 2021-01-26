package com.example.myprepare

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myprepare.activity.*
import com.example.myprepare.annotation_test.AnnotationTestActivity
import com.example.myprepare.jet_pack.JetPackTestActivity
import com.example.myprepare.module.RouterDetail
import com.example.myprepare.plugin_and_hot_fix.PluginAndHotFixActivity
import com.example.myprepare.recyclerview.MyRecyclerViewAdapter
import com.example.myprepare.view_test.*
import com.example.myprepare.view_test.left_age.LeftAgeAnimationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private lateinit var myAdapter: MyRecyclerViewAdapter

  companion object {

    private const val REQUEST_CODE = 1

    private val PERMISSION: Array<String> = arrayOf(
      Manifest.permission.INTERNET,
      Manifest.permission.WRITE_EXTERNAL_STORAGE,
      Manifest.permission.CAMERA,
      Manifest.permission.READ_EXTERNAL_STORAGE
    )
  }

  private val missingPermissionList: MutableList<String> = ArrayList()

  private fun checkPermission() {
    PERMISSION.forEach {
      ContextCompat.checkSelfPermission(this, it)
      if (ContextCompat.checkSelfPermission(this, it) != PERMISSION_GRANTED) {
//        Logger.d("missing permission = $it")
        missingPermissionList.add(it)
      } else {
//        Logger.d("checkPermission: has permission $it")
      }
    }

    if (missingPermissionList.isNotEmpty()) {
      ActivityCompat.requestPermissions(this, PERMISSION, REQUEST_CODE)
    }
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    Logger.d("requestCode = $requestCode")
//    Logger.d("permission")
//    Logger.d(permissions)
//    Logger.d("grantResults")
//    Logger.d(grantResults)

    if (requestCode == REQUEST_CODE) {
      for (i in permissions.indices) {
        if (grantResults[i] == PERMISSION_GRANTED) {
          missingPermissionList.remove(permissions[i])
        }
      }
    }
  }

  override fun onResume() {
    super.onResume()
    checkPermission()
  }

  private val routeDetailList: MutableList<RouterDetail> = mutableListOf(
    RouterDetail("RxJava3", RxJava3Activity::class.java),
    RouterDetail("ViewSize", ViewSizeActivity::class.java),
    RouterDetail("EditTextView", EditTextActivity::class.java),
    RouterDetail("DrawerLayoutTest", DrawerLayoutActivity::class.java),
    RouterDetail("ResTest", ResTestActivity::class.java),
    RouterDetail("RecyclerTest", RecyclerTestActivity::class.java),
    RouterDetail("FragmentTest", FragmentTestActivity::class.java),
    RouterDetail("ServiceTest", ServiceTestActivity::class.java),
    RouterDetail("Retrofit", RetrofitActivity::class.java),
    RouterDetail("OkHttp", OkHttpActivity::class.java),
    RouterDetail("View", ViewActivity::class.java),
    RouterDetail("View2", ViewActivity2::class.java),
    RouterDetail("View3", ViewActivity3::class.java),
    RouterDetail("View4", ViewActivity4::class.java),
    RouterDetail("ThreadTest", ThreadTestActivity::class.java),
    RouterDetail("IOTest", IOActivity::class.java),
    RouterDetail("Animation", AnimationActivity::class.java),
    RouterDetail("Animation2", AnimationActivity2::class.java),
    RouterDetail("BitmapDrawable", BitmapDrawableActivity::class.java),
    RouterDetail("MaterialEditText", MaterialEditTextActivity::class.java),
    RouterDetail("MeasureLayout", MeasureLayoutActivity::class.java),
    RouterDetail("TagLayout", TagLayoutActivity::class.java),
    RouterDetail("ViewTest", ViewTestActivity::class.java),
    RouterDetail("TouchEventTest", TouchEventTestActivity::class.java),
    RouterDetail("ScalableImageView", ScalableImageViewActivity::class.java),
    RouterDetail("BadgeTest", BadgeTestActivity::class.java),
    RouterDetail("PluginAndHotFix", PluginAndHotFixActivity::class.java),
    RouterDetail("AnnotationTest", AnnotationTestActivity::class.java),
    RouterDetail("GenericTest", GenericTestActivity::class.java),
    RouterDetail("CoroutinesTest", CoroutinesTestActivity::class.java),
    RouterDetail("HashMapTest", HashMapTestActivity::class.java),
    RouterDetail("ActivityTest", ActivityTestActivity::class.java),
    RouterDetail("SerializableTest", SerializableTestActivity::class.java),
    RouterDetail("LeftAgeAnimation", LeftAgeAnimationActivity::class.java),
    RouterDetail("JetPackTest", JetPackTestActivity::class.java)
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    checkPermission()
//    Logger.d("start")
    initData()
    initView()

    drawBadge()
  }

  private fun initData() {

    myAdapter = MyRecyclerViewAdapter(routeDetailList)
    myAdapter.setOnItemClickListener(object : MyRecyclerViewAdapter.OnItemClickListener {
      override fun onItemClick(title: String, position: Int) {
//        Logger.d("position: $position, title: $title")
        startActivity(Intent(this@MainActivity, routeDetailList[position].activityClass))
      }
    })
  }

  private fun initView() {
    my_recycler_view.adapter = myAdapter
    my_recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    my_recycler_view.addItemDecoration(
      DividerItemDecoration(
        this,
        DividerItemDecoration.VERTICAL
      )
    )
    my_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      private var scrollY: Int = 0

      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        scrollY += dy
//        Logger.d("onScrolled: dy = $dy, scrollY = $scrollY")
      }

      override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
//        Logger.d("recyclerView = $recyclerView, newState = $newState")
      }
    })
  }
}