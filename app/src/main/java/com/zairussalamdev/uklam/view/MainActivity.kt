package com.zairussalamdev.uklam.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.uklam.R
import com.zairussalamdev.uklam.adapter.EventAdapter
import com.zairussalamdev.uklam.adapter.RecommendAdapter
import com.zairussalamdev.uklam.model.ListItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val items = intent.getParcelableExtra<ListItem>("items").items
        rvRecommend.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
        rvRecommend.adapter = RecommendAdapter(applicationContext, items) {}
        rvEvent.layoutManager = LinearLayoutManager(applicationContext)
        rvEvent.adapter = EventAdapter(applicationContext, items) {}
    }

}
