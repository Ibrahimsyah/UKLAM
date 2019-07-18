package com.zairussalamdev.uklam.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.uklam.R
import com.zairussalamdev.uklam.adapter.EventAdapter
import com.zairussalamdev.uklam.model.ListItem
import com.zairussalamdev.uklam.presenter.MainPresenter
import com.zairussalamdev.uklam.presenter.MainView
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity

class SearchActivity : AppCompatActivity(), MainView {
    override fun showLoading() {
        searchNoResult.visibility = View.GONE
        pbSearch.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbSearch.visibility = View.GONE
    }

    override fun showData(items: ListItem) {
        if (items.items.isNotEmpty()) {
            rvSearch.layoutManager = LinearLayoutManager(this)
            rvSearch.adapter = EventAdapter(this, items.items) {
                startActivity<DetailActivity>("item" to it)
            }
        } else {
            searchNoResult.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(searchToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val presenter = MainPresenter(this)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { presenter.grabDataByQuery(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
