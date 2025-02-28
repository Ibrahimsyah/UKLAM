package com.zairussalamdev.uklam.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.uklam.R
import com.zairussalamdev.uklam.adapter.EventAdapter
import com.zairussalamdev.uklam.db.database
import com.zairussalamdev.uklam.model.FavItem
import com.zairussalamdev.uklam.model.Item
import kotlinx.android.synthetic.main.activity_favourite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class FavouriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        setSupportActionBar(favouriteToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var favItems: List<FavItem> = mutableListOf()
        database.use {
            val res = select(FavItem.TABLE_FAV)
            favItems = res.parseList(classParser())
        }
        val items = initData(favItems)
        if (items.isEmpty()) {
            favNoData.visibility = View.VISIBLE
            rvFavourite.visibility = View.GONE
        } else {
            rvFavourite.layoutManager = LinearLayoutManager(this)
            rvFavourite.adapter = EventAdapter(this, items) {
                startActivity<DetailActivity>("item" to it)
            }
        }
    }

    private fun initData(favItems: List<FavItem>): List<Item> {
        val items: MutableList<Item> = mutableListOf()
        for (item in favItems) {
            items.add(
                Item(
                    item.itemId,
                    item.name,
                    item.shortName,
                    item.description,
                    item.category,
                    item.photo,
                    item.location,
                    item.address
                )
            )
        }
        return items
    }

    override fun onResume() {
        super.onResume()
        var favItems: List<FavItem> = mutableListOf()
        database.use {
            val res = select(FavItem.TABLE_FAV)
            favItems = res.parseList(classParser())
        }
        val items = initData(favItems)
        if (items.isEmpty()) {
            favNoData.visibility = View.VISIBLE
            rvFavourite.visibility = View.GONE
        } else {
            rvFavourite.layoutManager = LinearLayoutManager(this)
            rvFavourite.adapter = EventAdapter(this, items) {
                startActivity<DetailActivity>("item" to it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
