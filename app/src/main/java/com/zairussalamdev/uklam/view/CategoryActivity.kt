package com.zairussalamdev.uklam.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.uklam.R
import com.zairussalamdev.uklam.adapter.EventAdapter
import com.zairussalamdev.uklam.model.ListItem
import com.zairussalamdev.uklam.presenter.MainPresenter
import com.zairussalamdev.uklam.presenter.MainView
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_main.*

class CategoryActivity : AppCompatActivity(), MainView {
    override fun showLoading() {
        pbCategory.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbCategory.visibility = View.GONE
    }

    override fun showData(items: ListItem) {
        rvCategory.layoutManager = LinearLayoutManager(applicationContext)
        rvCategory.adapter = EventAdapter(applicationContext, items.items) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val category = intent.getStringExtra("category")
        val title = when (category) {
            "1" -> "Wisata"
            "2" -> "Kuliner"
            "3" -> "Budaya"
            "4" -> "Belanja"
            else -> "Tidak Ditemukan"
        }
        categoryToolbar.title = title
        setSupportActionBar(categoryToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val presenter = MainPresenter(this)
        presenter.grabDataByCategory(category)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home)super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
