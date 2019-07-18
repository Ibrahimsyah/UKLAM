package com.zairussalamdev.uklam.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.uklam.R
import com.zairussalamdev.uklam.adapter.EventAdapter
import com.zairussalamdev.uklam.db.database
import com.zairussalamdev.uklam.model.Item
import kotlinx.android.synthetic.main.activity_favourite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavouriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        setSupportActionBar(favouriteToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var items: List<Item> = mutableListOf()
        database.use {
            val res = select(Item.TABLE_FAV)
            items = res.parseList(classParser())
        }
        if (items.isEmpty()) favNoData.visibility = View.VISIBLE
        else {
            rvFavourite.layoutManager = LinearLayoutManager(this)
            rvFavourite.adapter = EventAdapter(this, items) {
                TODO("Add Listener")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
