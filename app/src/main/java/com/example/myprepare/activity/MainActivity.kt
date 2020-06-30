package com.example.myprepare.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myprepare.R
import com.example.myprepare.recyclerview.MyRecyclerViewAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var activityMap: Map<String, Intent>
    private lateinit var titleList: Array<String>
    private lateinit var myAdapter: MyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.d("start")
        initData()
        initView()
    }

    private fun initData() {
        titleList = arrayOf("Rxjava2")
        activityMap = mapOf(Pair("Rxjava2", Intent(this, Rxjava2Activity::class.java)))

        myAdapter = MyRecyclerViewAdapter(titleList)
        myAdapter.setOnItemClickListener(object : MyRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(title: String, position: Int) {
                Logger.d("position: $position, title: $title")
                if (activityMap.containsKey(title)) {
                    startActivity(activityMap[title])
                }
            }
        })
    }

    private fun initView() {
        my_recycler_view.adapter = myAdapter
        my_recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        my_recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
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