package com.zairussalamdev.uklam.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.zairussalamdev.uklam.R
import com.zairussalamdev.uklam.adapter.EventAdapter
import com.zairussalamdev.uklam.adapter.RecommendAdapter
import com.zairussalamdev.uklam.model.Item
import com.zairussalamdev.uklam.model.ListItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_favorite_white)
        setSupportActionBar(toolbar)

        val items = intent.getParcelableExtra<ListItem>("items").items
        val recommendItems = initRecommend(items)
        rvRecommend.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
        rvRecommend.adapter = RecommendAdapter(applicationContext, recommendItems) {}
        rvEvent.layoutManager = LinearLayoutManager(applicationContext)
        rvEvent.adapter = EventAdapter(applicationContext, items) {}

        btnWisata.onClick {
            startActivity<CategoryActivity>("category" to "1")
        }
        btnKuliner.onClick {
            startActivity<CategoryActivity>("category" to "2")
        }
        btnBudaya.onClick {
            startActivity<CategoryActivity>("category" to "3")
        }
        btnBelanja.onClick {
            startActivity<CategoryActivity>("category" to "4")
        }
    }

    private fun initRecommend(items : List<Item>) : List<Item>{
        val recommendItems : MutableList<Item> = mutableListOf()
        for(i in items){
            if(i.shortName != "")recommendItems.add(i)
        }
        return recommendItems
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) startActivity<FavouriteActivity>()
        if (item?.itemId == R.id.menu_search) startActivity<SearchActivity>()
        return super.onOptionsItemSelected(item)
    }
}
