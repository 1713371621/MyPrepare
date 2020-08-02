package com.example.myprepare.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myprepare.R
import com.example.myprepare.module.RouterDetail
import com.example.myprepare.recyclerview.MyRecyclerViewAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var myAdapter: MyRecyclerViewAdapter

    private val routeDetailList: MutableList<RouterDetail> = mutableListOf(
        RouterDetail("Rxjava2",Rxjava2Activity::class.java),
        RouterDetail("ViewSize",ViewSizeActivity::class.java),
        RouterDetail("EditTextView",EditTextActivity::class.java),
        RouterDetail("DrawerLayoutTest",DrawerLayoutActivity::class.java),
        RouterDetail("ResTest",ResTestActivity::class.java),
        RouterDetail("RecyclerTest",RecyclerTestActivity::class.java),
        RouterDetail("FragmentTest",FragmentTestActivity::class.java),
        RouterDetail("ServiceTest",ServiceTestActivity::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.d("start")
        initData()
        initView()
    }

    private fun initData() {

        myAdapter = MyRecyclerViewAdapter(routeDetailList)
        myAdapter.setOnItemClickListener(object : MyRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(title: String, position: Int) {
                Logger.d("position: $position, title: $title")
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
                Logger.d("onScrolled: dy = $dy, scrollY = $scrollY")
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
}